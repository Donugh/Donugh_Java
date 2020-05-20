package com.dangdang.usercontroller.service;

import com.dangdang.usercontroller.entity.Pam.AppUpdatePam;
import com.dangdang.usercontroller.entity.Pam.InsAppTablePam;
import com.dangdang.usercontroller.utils.Result;

/**
 * @Author HuDong
 * @Create 2020-05-10 17:42
 **/
public interface AppService {

    //获取所有App列表
    Result allAppList(String id);

    //分页获取App列表
    Result pageAppList(int page,String id);

    //修改广告状态
    Result updateAppStatus(int id, int status);

    // 根据id获取App信息
    Result getAppById(Integer id);

    // 添加App
    Result addApp(InsAppTablePam insAppTablePam);

    //修改App
    Result updateApp(AppUpdatePam appUpdatePam);

    //删除App
    Result delAppById(Integer id);
}
