package com.dangdang.usercontroller.service;

import com.dangdang.usercontroller.entity.AndroidMessagePro;
import com.dangdang.usercontroller.utils.Result;

public interface UserService {
    // 用户登录
    Result userLoginService(String name,String password);


    // 添加用户设备信息
    Result saveAndroidMsgPro(AndroidMessagePro messagePro);

//    Result saveAndroidMsg(AndroidMessage androidMessage);

    // 测试方法
    Result saveAndroidMsgTest(AndroidMessagePro messagePro);
}
