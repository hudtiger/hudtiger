package com.light.springboot.global;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.GroupResponse;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.TermsResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.util.NamedList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.light.springboot.solr.UserInfo;

public class SolrUtil {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	private String solrUrl = "http://localhost:8983/solr/my_newss";

	public Object handle(IQueryDefine define, IQueryHandle handler) {
		System.out.println(solrUrl);
		HttpSolrClient solrClient = new HttpSolrClient(solrUrl);
		SolrQuery query = new SolrQuery();
		query.set("q", "*:*"); // all
		query.setStart(0); // start
		query.setRows(10); // end
		query.setSort("id", SolrQuery.ORDER.desc); // order

		query = define.define(query); // 重新定义

		QueryResponse rsp = null;
		try {
			rsp = solrClient.query(query);
			return handler.handle(rsp);
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			close(solrClient);
		}
		return null;
	}

	private void close(HttpSolrClient solrClient) {
		try {
			solrClient.close();
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}

	public SolrDocument getObject(String id) {
		HttpSolrClient solrClient = new HttpSolrClient("http://localhost:8983/solr/my_newss");
		SolrDocument doc = null;
		try {
			doc = solrClient.getById(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			close(solrClient);
		}
		return doc;
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> getObjects(IQueryDefine define, Class<T> entityType) {
		return (List<T>) this.handle(define, rsp -> rsp.getBeans(entityType));
	}

	// 查询总条数，该总条数是符合该条件下的总条数，并不是pageSize的数量
	public long getSize(IQueryDefine define) {
		return (long) this.handle(define, rsp -> rsp.getResults().getNumFound());
	}

	public static void main(String[] args) {

		SolrUtil solr = new SolrUtil();
		/*
		 * System.out.println(solr.getSize(query->query));
		 * 
		 * SolrDocument doc = solr.getObject("5");
		 * System.out.println(doc.getFieldValue("FNote"));
		 * 
		 * List<UserInfo> list = solr.getObjects(query->{
		 * query.set("q","FNote:hi"); query.setFields("id","FDate","FNote");
		 * //指定查询字符串 return query; }, UserInfo.class);
		 * list.forEach(u->System.out.println(u));
		 */

		solr.handle((SolrQuery query) -> {
			query = new SolrQuery();
			query.set("q", "*:*");
			query.set("fq","FContent:qn od");
			// query.setRows(0);
			query.set("defType","edismax");//开启权重配置
			query.set("bq","FContent:od^10 qn^1");//设置权重
			query.setFacet(true); //开启聚合
			query.addFacetField("FYear");//设置分组条件
			return query;
		}, rsp -> {
			rsp.getFacetFields().forEach(facert -> {
				facert.getValues().forEach(count -> System.out.println(count.getName() + ":" + count.getCount()));
			});
			return null;
		});
	}

}

interface IQueryDefine {
	SolrQuery define(SolrQuery query);
}

interface IQueryHandle {
	Object handle(QueryResponse rsp);
}
