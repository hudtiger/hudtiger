package com.light.springboot.model;

import java.io.IOException;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.alibaba.fastjson.annotation.JSONField;

public class User  implements  RowMapper<User>, Serializable{

    private Integer id;
	private String username;
    
    private String password;
    
    @JSONField(format="yyyy-MM-dd")
    private Date birthday;

    
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Override
	public User mapRow(ResultSet res, int rowIdx) throws SQLException {
		// TODO Auto-generated method stub
		User user = new User();
		user.setId(res.getInt("Id"));
		user.setUsername(res.getString("companyId"));
		user.setPassword(res.getString("content"));
		user.setBirthday(new Date());
		return user;
	}
}
