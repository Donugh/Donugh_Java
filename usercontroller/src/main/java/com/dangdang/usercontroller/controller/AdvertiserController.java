package com.dangdang.usercontroller.controller;

import com.dangdang.usercontroller.entity.Pam.AdUpdatePam;
import com.dangdang.usercontroller.entity.Pam.InsAdTablePam;
import com.dangdang.usercontroller.entity.Pam.RanDomAdPam;
import com.dangdang.usercontroller.service.AdvertiserService;
import com.dangdang.usercontroller.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 广告增删改查控制器
 * @Author HuDong
 * @Create 2020-05-06 14:43
 **/
@Slf4j
@RestController
//@RequestMapping("advertiser/*")
public class AdvertiserController {
    @Autowired
    private AdvertiserService advertiserService;

    public AdvertiserController(AdvertiserService advertiserService) {
        this.advertiserService = advertiserService;
    }


    // 获取所有广告列表
    @GetMapping("/allAdList")
    public Result allAdList(){
        return advertiserService.allAdList();
    }

    // 分页获取广告列表
    @PostMapping("/adList")
    public Result pageAdList(int page){
        return advertiserService.pageAdList(page);
    }

    //修改广告状态
    @PostMapping("/updateAdStatus")
    public Result updateAdStatus(AdUpdatePam adUpdatePam){
        return  advertiserService.updateAdStatus(adUpdatePam.getId(),adUpdatePam.getStatus());
    }


    // 根据id获取广告信息
    @GetMapping("/getAdById")
    public Result getAdById(Integer id ){
        return advertiserService.getAdById(id);
    }


    // 添加广告
    @PostMapping("/addAd")
    public Result addAd(InsAdTablePam adTable){
        return advertiserService.addAd(adTable);
    }

    //修改广告
    @PostMapping("/updateAd")
    public Result updateAd(AdUpdatePam adUpdatePam){
        return advertiserService.updateAd(adUpdatePam);
    }

    // 删除广告
    @PostMapping("/delAdById")
    public Result delAdById(Integer id){
        return advertiserService.delAdById(id);
    }

    //搜索广告
    @PostMapping("/searchAd")
    public Result searchAd(String packageName ,String appName){
        return advertiserService.searchAd(packageName,appName);
    }

    //查询所有包名
    @PostMapping("/searchAllPackage")
    public  Result searchAllPackage(){
        return advertiserService.searchAllPackage();
    }

    //根据包名查询广告
    @PostMapping("/getAdListByPackageName")
    public Result getAdListByPackageName(String packageName){
        return advertiserService.getAdListByPackageName(packageName);
    }


    //根据adType查询广告
    @PostMapping("/getIndexByAdType")
    public Result getIndexByAdType(String adType){
        return advertiserService.getIndexByAdType(adType);
    }

    //根据包名查询广告
    @PostMapping("/getIndexByPackageName")
    public Result getIndexByPackageName(@RequestBody RanDomAdPam ranDomAdPam){
        String pn= ranDomAdPam.getPackageName().trim();
        return advertiserService.getIndexByPackageName(pn);
    }
}
