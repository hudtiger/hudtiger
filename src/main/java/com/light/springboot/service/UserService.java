package com.light.springboot.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.light.springboot.model.User;

@Service
public class UserService {

	@Autowired
	@Qualifier("globalJdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	public List<User> findAllByCompanyId(long companyId) throws SQLException {
		List<User> list = null;
		StringBuffer sql = new StringBuffer("");
		sql.append("select id,companyId,content,mapId from tblMaster where companyId="+companyId);	
		list = jdbcTemplate.query(sql.toString(), new User());
		return list;
	}
	
	@Transactional
	public void updateUserByUserId(long userId, User user) {
		// TODO Auto-generated method stub
		String sql = String.format(
				"replace into tblMaster(id,companyId,content,mapId) values(%d,'%s','%s',%d)",
				user.getId(),
				user.getUsername(),
				user.getPassword(),
				userId);
	  jdbcTemplate.execute(sql);
	}


}