package com.light.springboot.mapper.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.light.springboot.mapper.UserInfoMapper;
import com.light.springboot.mapper.Bean.UserInfo;
import com.light.springboot.mapper.service.UserInfoService;

@Service
@Primary //标记优先注入
public class UserInfoServiceImpl implements UserInfoService {
	@Autowired
	private UserInfoMapper userMapper;

	public UserInfo getUserById(Integer id) {
		return userMapper.getUserById(id);
	}

	public List<UserInfo> getUserList() {
		return userMapper.getUserList();
	}

	public int add(UserInfo user) {
		return userMapper.add(user);
	}

	public int update(Integer id, UserInfo user) {
		return userMapper.update(id, user);
	}

	public int delete(Integer id) {
		return userMapper.delete(id);
	}
}
