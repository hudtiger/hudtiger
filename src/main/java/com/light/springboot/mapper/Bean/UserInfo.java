package com.light.springboot.mapper.Bean;

import java.util.Date;

import org.apache.ibatis.annotations.Mapper;

import com.alibaba.fastjson.annotation.JSONField;

public class UserInfo {
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
	public String toString(){
		return String.format("ID:%d;UserName:%s;Pwd:%s;Birthday:%s", this.id,this.username,this.password,this.birthday);
	}
}
