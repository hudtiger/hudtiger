package com.light.springboot.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.light.springboot.model.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value = "swaggerjson测试", tags = { "SwaggerController" })
@RestController
@RequestMapping("swaggerjson")
public class SwaggerController {

	@ApiOperation("获取用户信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "用户Id", dataType = "int", paramType = "path"),
		@ApiImplicitParam(name = "name", value = "用户名", dataType = "string", paramType = "path"),
				})
    @GetMapping("/test/{name}/{id}")
    public User test(@PathVariable("id") Integer id,@PathVariable("name") String name) {
        User user = new User();

        user.setId(id);
        user.setUsername(name);
        user.setPassword("jack123");
        user.setBirthday(new Date());

        return user;
    }
	
	@ApiOperation("获取用户信息")
    @ApiImplicitParam(name = "name", value = "用户名", dataType = "string", paramType = "query")
    @GetMapping("/test1")
    public User test1(@RequestParam("name") String name) {
        User user = new User();

        user.setId(1);
        user.setUsername(name);
        user.setPassword("jack123");
        user.setBirthday(new Date());

        return user;
    }
	
	@ApiOperation("获取用户信息")
    @ApiImplicitParam(name = "user", value = "用户", dataType = "User", paramType = "body")
    @PostMapping("/test2")
    public User test2(@RequestBody() User user) {
        user.setId(1);
        user.setUsername(user.getUsername()+ " wapper");
        user.setPassword("jack123");
        user.setBirthday(user.getBirthday());

        return user;
    }
}
