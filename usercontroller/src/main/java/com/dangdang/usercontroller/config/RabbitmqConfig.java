package com.dangdang.usercontroller.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author songxuan
 * @Create 2020-04-24 9:25
 **/
@Configuration
public class RabbitmqConfig {

    // 声明队列
    @Bean()
    public Queue QUEUE(){
        return new Queue("AndroidMessage",true);
    }

    @Bean()
    public Queue QUEUE2(){
        return new Queue("QueueTest",true);
    }

    @Bean
    Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
