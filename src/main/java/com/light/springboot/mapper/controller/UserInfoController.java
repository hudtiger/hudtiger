package com.light.springboot.mapper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.light.springboot.mapper.Bean.JsonResult;
import com.light.springboot.mapper.Bean.UserInfo;
import com.light.springboot.mapper.service.UserInfoService;
import com.light.springboot.mapper.service.impl.UserInfoServiceImpl;

@RestController
public class UserInfoController {
	//@Autowired
	//private UserInfoService userService;
	
	@Autowired
	private UserInfoServiceImpl userService;

	/**
	 * 根据ID查询用户
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "user/{id}", method = RequestMethod.GET)
	public ResponseEntity<JsonResult> getUserById (@PathVariable(value = "id") Integer id){
		JsonResult r = new JsonResult();
		try {
			UserInfo user = userService.getUserById(id);
			r.setResult(user);
			r.setStatus("ok");
		} catch (Exception e) {
			r.setResult(e.getClass().getName() + ":" + e.getMessage());
			r.setStatus("error");
			e.printStackTrace();
		}
		return ResponseEntity.ok(r);
	}

	/**
	 * 查询用户列表
	 * @return
	 */
	@RequestMapping(value = "users", method = RequestMethod.GET)
	public ResponseEntity<JsonResult> getUserList (){
		JsonResult r = new JsonResult();
		try {
			List<UserInfo> users = userService.getUserList();
			r.setResult(users);
			r.setStatus("ok");
		} catch (Exception e) {
			r.setResult(e.getClass().getName() + ":" + e.getMessage());
			r.setStatus("error");
			e.printStackTrace();
		}
		return ResponseEntity.ok(r);
	}

	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "user", method = RequestMethod.POST)
	public ResponseEntity<JsonResult> add (@RequestBody UserInfo user){
		JsonResult r = new JsonResult();
		try {
			int orderId = userService.add(user);
			if (orderId < 0) {
				r.setResult(orderId);
				r.setStatus("fail");
			} else {
				r.setResult(orderId);
				r.setStatus("ok");
			}
		} catch (Exception e) {
			r.setResult(e.getClass().getName() + ":" + e.getMessage());
			r.setStatus("error");

			e.printStackTrace();
		}
		return ResponseEntity.ok(r);
	}

	/**
	 * 根据id删除用户
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<JsonResult> delete (@PathVariable(value = "id") Integer id){
		JsonResult r = new JsonResult();
		try {
			int ret = userService.delete(id);
			if (ret < 0) {
				r.setResult(ret);
				r.setStatus("fail");
			} else {
				r.setResult(ret);
				r.setStatus("ok");
			}
		} catch (Exception e) {
			r.setResult(e.getClass().getName() + ":" + e.getMessage());
			r.setStatus("error");

			e.printStackTrace();
		}
		return ResponseEntity.ok(r);
	}

	/**
	 * 根据id修改用户信息
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<JsonResult> update (@PathVariable("id") Integer id, @RequestBody UserInfo user){
		JsonResult r = new JsonResult();
		try {
			int ret = userService.update(id, user);
			if (ret < 0) {
				r.setResult(ret);
				r.setStatus("fail");
			} else {
				r.setResult(ret);
				r.setStatus("ok");
			}
		} catch (Exception e) {
			r.setResult(e.getClass().getName() + ":" + e.getMessage());
			r.setStatus("error");

			e.printStackTrace();
		}
		return ResponseEntity.ok(r);
	}
}
