package com.light.springboot.mapper.service;

import java.util.List;

import com.light.springboot.mapper.Bean.UserInfo;

public interface UserInfoService {
	UserInfo getUserById(Integer id);

	public List<UserInfo> getUserList();

	public int add(UserInfo user);

	public int update(Integer id, UserInfo user);

	public int delete(Integer id);
	
	
}
