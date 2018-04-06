package com.light.springboot.solr;

import java.util.Date;

import org.apache.solr.client.solrj.beans.Field;

public class UserInfo{
	@Field
	private String id;
	@Field("FDate")
	private Date date;
	@Field("FNote")
	private String note;

	
	@Override
	public String toString(){
		return String.format("SolrObject:ID:%S;Date:%s;Note:%s", this.id,this.date,this.note);
	}
}
