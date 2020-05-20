package com.dangdang.usercontroller.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author songxuan
 * @Create 2020-04-30 9:49
 **/
@Configuration
public class WebConfigurer implements WebMvcConfigurer{
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(SpringInterceptorConfig()).addPathPatterns("/**");
    }
    @Bean
    public SpringInterceptorConfig SpringInterceptorConfig() {
        return new SpringInterceptorConfig();
    }
}
