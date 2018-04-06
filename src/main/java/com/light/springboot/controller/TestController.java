package com.light.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/")
    public String helloworld() {
    	Integer i = 1/0;
        return "helloworld";
    }
    
    @GetMapping("/helloworld2")
    public String helloworld2() {
        return "helloworld.wew..asd.";
    }
}