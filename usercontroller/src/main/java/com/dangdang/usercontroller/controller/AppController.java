package com.dangdang.usercontroller.controller;

import com.dangdang.usercontroller.entity.Pam.AppUpdatePam;
import com.dangdang.usercontroller.entity.Pam.InsAppTablePam;
import com.dangdang.usercontroller.service.AppService;
import com.dangdang.usercontroller.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 应用增删改查控制器
 * @Author HuDong
 * @Create 2020-05-10 10:02
 **/
@Slf4j
@RestController
@RequestMapping("app/*")
public class AppController {
    @Autowired
    private AppService appService;

    public AppController(AppService appService) {
        this.appService = appService;
    }


    // 获取所有应用列表
    @GetMapping("/allAppList")
    public Result allAppList(String id){
        return appService.allAppList(id);
    }

    // 分页获取应用列表
    @PostMapping("/appList")
    public Result pageAppList(int page,String id){
        return appService.pageAppList(page,id);

    }

    //修改应用状态
    @PostMapping("/updateAppStatus")
    public Result updateAppStatus(AppUpdatePam appUpdatePam){
        return  appService.updateAppStatus(appUpdatePam.getId(),appUpdatePam.getEnabled());
    }


    // 根据id获取应用信息
    @PostMapping("/getAppById")
    public Result getAppById(Integer id ){
        return appService.getAppById(id);
    }


    // 添加应用
    @PostMapping("/addApp")
    public Result addApp(InsAppTablePam insAppTablePam){
        return appService.addApp(insAppTablePam);
    }

    //修改应用
    @PostMapping("/updateApp")
    public Result updateApp(AppUpdatePam appUpdatePam){
        return appService.updateApp(appUpdatePam);
    }

    // 删除应用
    @PostMapping("/delAppById")
    public Result delAppById(Integer id){
        return appService.delAppById(id);
    }
}
