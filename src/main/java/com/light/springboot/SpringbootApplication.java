package com.light.springboot;


import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletContextInitializer;

import com.light.springboot.global.ListenerTest;
import com.light.springboot.global.ServletTest;
import com.light.springboot.global.TimeFilter;


@SpringBootApplication
@MapperScan("com.light.springboot.mapper") //注册mybatis
public class SpringbootApplication {
	private static final Logger LOG = LoggerFactory.getLogger(SpringbootApplication.class); 
	
	
    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
        LOG.warn("Spring Restful Service：");  
    }
}

/* 直接注册过滤器、监听器、Servlet
 @SpringBootApplication
public class SpringbootWebApplication implements ServletContextInitializer {
    
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // 配置 Servlet
        servletContext.addServlet("servletTest",new ServletTest())
                      .addMapping("/servletTest");
        // 配置过滤器
        servletContext.addFilter("timeFilter",new TimeFilter())
                      .addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST),true,"/*");
        // 配置监听器
        servletContext.addListener(new ListenerTest());
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootWebApplication.class, args);
    }
}*/