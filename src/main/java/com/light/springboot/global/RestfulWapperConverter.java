package com.light.springboot.global;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotWritableException;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
/*
 * 用于自动包装restful应用下的通信包
 */
public class RestfulWapperConverter extends FastJsonHttpMessageConverter{
	private static final Logger LOG = LoggerFactory.getLogger(RestfulWapperConverter.class); 
	@Override
	public void write(Object t, //
            Type type, //
            MediaType contentType, //
            HttpOutputMessage outputMessage //
            ) throws HttpMessageNotWritableException, IOException{
		//System.out.println(type.toString());
		if(type.toString().indexOf("springfox")>-1){
			super.write(t, type, contentType, outputMessage);
		}else{
			Map<String,Object> map = new HashMap<String,Object>();
	        map.put("code", 200);
	        map.put("msg", String.format("%s is warpped", t));
	        map.put("data", t);
			super.write(map, type, contentType, outputMessage);
		}
	}
	
	/*public Object read(Type type, //
            Class<?> contextClass, //
            HttpInputMessage inputMessage //
	) throws IOException, HttpMessageNotReadableException {
		if(contextClass.getTypeName().indexOf("springfox")>0
			||contextClass.getTypeName().indexOf("SwaggerUI")>0){
			return super.read(type, contextClass,inputMessage);
		}
		else{
			Object obj = super.read(type, contextClass,inputMessage);
			return ((Map<String,Object>)obj).get("data");
		}
	}*/
}