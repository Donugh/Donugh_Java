package com.dangdang.usercontroller.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author songxuan
 * @Create 2020-04-30 9:37
 **/
@Configuration
public class SpringInterceptorConfig implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String sign = request.getHeader("sign");
        if(sign == null){
          return false;
        }
        if(sign.equals("%g$RPIz$ilRhs##nAM9w%W%HcU&yu5dhfocayTIg5zyCkxrRyugBykzAecf0uT9a")){
            return true;
        }else{
            return false;
        }
    }
}
