package com.dangdang.usercontroller.service.impl;

import com.alibaba.fastjson.JSON;
import com.dangdang.usercontroller.entity.AndroidMessagePro;
import com.dangdang.usercontroller.entity.User;
import com.dangdang.usercontroller.mapper.UserMapper;
import com.dangdang.usercontroller.service.UserService;
import com.dangdang.usercontroller.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService{
    private final String QUEUE_NAME = "AndroidMessage";

    private final String QUEUE_TEST = "QueueTest";

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    // 用户登录
    @Override
    public Result userLoginService(String name,String password) {
       User user = userMapper.userLogin(name, password);
        if(user == null){
           return  Result.error();
        }else{
            return Result.ok();
        }
    }


//    @Override
//    public Result saveAndroidMsg(AndroidMessage androidMessage) {
//        try {
//            log.info("保存用户设备信息："+androidMessage);
//            rabbitTemplate.convertAndSend(QUEUE_NAME, JSON.toJSON(androidMessage));
//            return Result.ok();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Result.error("服务器出现异常");
//        }
//    }

    /**
     * 保存用户设备信息，数据量大交给MQ处理
     * @param messagePro
     * @return
     */
    @Override
    public Result saveAndroidMsgPro(AndroidMessagePro messagePro) {
        try {
            log.info("保存用户设备信息pro："+messagePro);
            rabbitTemplate.convertAndSend(QUEUE_NAME, JSON.toJSON(messagePro));
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("服务器出现异常");
        }
    }

    /**
     * 测试方法
     * @param messagePro
     * @return
     */
    @Override
    public Result saveAndroidMsgTest(AndroidMessagePro messagePro) {
        try {
            log.info("测试保存用户设备信息测试："+messagePro);
            rabbitTemplate.convertAndSend(QUEUE_TEST, JSON.toJSON(messagePro));
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("服务器出现异常");
        }
    }


}
