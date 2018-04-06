package com.light.springboot.controller;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.light.springboot.model.User;
import com.light.springboot.service.UserService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/mysql")
public class mysqlController {

	@Autowired
	private UserService userService;
	
	@ApiOperation("获取用户信息")
    @GetMapping("/users")
    public List<User> findAllByCompanyId(@RequestParam("companyId") long companyId) throws SQLException {
        return userService.findAllByCompanyId(companyId);
    }
	
	@ApiOperation("更新用户信息")
    @PostMapping("/users/{id}")
    public void updateUserByUserId(
    		@PathVariable("id") long userId,
    		@RequestBody() User user) throws SQLException {
         userService.updateUserByUserId(userId,user);
    }
}
