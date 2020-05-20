package com.dangdang.usercontroller.service;

import com.dangdang.usercontroller.entity.Pam.AdUpdatePam;
import com.dangdang.usercontroller.entity.Pam.InsAdTablePam;
import com.dangdang.usercontroller.utils.Result;

/**
 * @Author HuDong
 * @Create 2020-05-06 17:42
 **/
public interface AdvertiserService {

    //获取所有广告商列表
    Result allAdList();

    //分页获取广告商列表
    Result pageAdList(int page);

    //修改广告状态
    Result updateAdStatus(int id, int status);

    // 根据id获取广告商信息
    Result getAdById(Integer id);

    // 添加广告商
    Result addAd(InsAdTablePam insAdTablePam);

    //修改广告商
    Result updateAd(AdUpdatePam adUpdatePam);

    //删除广告商
    Result delAdById(Integer id);

    //搜索广告
    Result searchAd(String packageName ,String appName);

    //根据广告类型查询广告信息
    Result getIndexByAdType(String adType);

    //根据包名查询广告信息
    Result getIndexByPackageName(String packageName);

    //查询所有包名
    Result searchAllPackage();

    //根据包名查询广告列表
    Result getAdListByPackageName(String packageName);
}
