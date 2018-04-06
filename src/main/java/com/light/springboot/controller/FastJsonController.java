package com.light.springboot.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.light.springboot.model.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;


@Controller
@RequestMapping("fastjson")
public class FastJsonController {

    @RequestMapping("/test")//会产生7种调用方法
    @CrossOrigin(origins="http://localhost:8088") //支持跨域访问
    @ResponseBody
    public User test() {
        User user = new User();
        
        user.setId(1);
        user.setUsername("jack");
        user.setPassword("jack123");
        user.setBirthday(new Date());
        
        return user;
    }
    
    
}