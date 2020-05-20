package com.dangdang.usercontroller.controller;

import com.dangdang.usercontroller.entity.AndroidMessagePro;
import com.dangdang.usercontroller.entity.Pam.RanDomAdPam;
import com.dangdang.usercontroller.service.AdAllocationService;
import com.dangdang.usercontroller.service.UserService;
import com.dangdang.usercontroller.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 广告配置
 * @Author songxuan
 * @Create 2020-04-28 10:31
 **/
@Slf4j
@RestController
public class AdAllocationController {

    @Autowired
    private AdAllocationService adAllocationService;

    @Autowired
    private UserService userService;


    // 获取一个随机广告
    @PostMapping("/randomAd")
    public Result randomAd(@RequestBody RanDomAdPam ranDomAdPam){
        return adAllocationService.randomAd(ranDomAdPam);
    }


    // 添加用户设备信息
    @PostMapping("/saveAndroidMsg")
    public Result saveAndroidMsgPro(@RequestBody AndroidMessagePro pro){
        return userService.saveAndroidMsgPro(pro);
    }

    // 测试添加用户设备信息
    @PostMapping("/saveAndroidMsgTest")
    public Result saveAndroidMsgTest(@RequestBody AndroidMessagePro pro){
        return userService.saveAndroidMsgTest(pro);
    }



}
