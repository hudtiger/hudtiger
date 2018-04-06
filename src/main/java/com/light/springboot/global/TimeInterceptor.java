package com.light.springboot.global;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class TimeInterceptor implements HandlerInterceptor {
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        
        System.out.println("========preHandle=========");
        ResourceHttpRequestHandler handlerO = (ResourceHttpRequestHandler)handler;
//        System.out.println(((ResourceHttpRequestHandler)handler).getBean().getClass().getName());
//        System.out.println(((ResourceHttpRequestHandler)handler).getMethod().getName());
        
        request.setAttribute("startTime", System.currentTimeMillis());
        return true;
    }
    
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {

        System.out.println("========postHandle=========");
        Long start = (Long) request.getAttribute("startTime");
        System.out.println("耗时:"+(System.currentTimeMillis() - start));
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception)
            throws Exception {

        System.out.println("========afterCompletion=========");
        Long start = (Long) request.getAttribute("startTime");
        System.out.println("耗时:"+(System.currentTimeMillis() - start));
        
        System.out.println(exception);
    }

}