package com.light.springboot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import io.swagger.models.Contact;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Configuration {
    
    @Bean
    public Docket accessToken() {
        return new Docket(DocumentationType.SWAGGER_2)
               .groupName("api")// 定义组
                .apiInfo(apiInfo()) // 配置说明
                .select() // 选择那些路径和 api 会生成 document
//                .apis(RequestHandlerSelectors.basePackage("com.light.springboot.controller")) // 拦截的包路径
   //             .apis(RequestHandlerSelectors.basePackage("com.light.springboot.mapper.controller"))
                .apis(new Predicate<RequestHandler>(){

					@Override
					public boolean apply(RequestHandler input) {
						// TODO Auto-generated method stub
						return RequestHandlerSelectors.basePackage("com.light.springboot.controller").apply(input)
								||RequestHandlerSelectors.basePackage("com.light.springboot.mapper.controller").apply(input);
					}
                	
                })
                .paths(PathSelectors.any())// 拦截的接口路径
                .build(); // 创建
                
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()//
                .title("Spring Boot")// 标题
                .description("spring boot Web 相关内容")// 描述
                .termsOfServiceUrl("http://www.extlight.com")//
                .version("1.0")// 版本
                .build();
    }
}