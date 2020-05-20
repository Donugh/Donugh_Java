package com.dangdang.usercontroller.service;

import com.dangdang.usercontroller.utils.Result;

public interface RecordService {
    //获取所有操作记录列表
    Result allRecordList();

    //分页获取操作记录列表
    Result pageRecordList(int page);

    // 根据id获取操作记录信息
    Result getRecordById(Integer id);

    //删除操作记录
    Result delRecordById(Integer id);

//    //搜索广告
//    Result searchRecord(String packageName ,String appName);

    //搜索广告
    Result searchRecord(String startTime ,String endTime,int page);

    //有包名、广告商的条件下查询用户信息
    Result searchRecordWithPackageNameAndAdType(String packageName,String adType ,String startTime ,String endTime,int page);

    //有包名、广告商的条件下删除用户记录
    Result delRecordWithFlag(String packageName,String adType ,int id);
}
