package com.dangdang.usercontroller.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.dangdang.usercontroller.entity.AdTable;
import com.dangdang.usercontroller.entity.Pam.AdUpdatePam;
import com.dangdang.usercontroller.entity.Pam.InsAdTablePam;
import com.dangdang.usercontroller.mapper.AdvertiserMapper;
import com.dangdang.usercontroller.service.AdvertiserService;
import com.dangdang.usercontroller.utils.DateUtil;
import com.dangdang.usercontroller.utils.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author HuDong
 * @Create 2020-05-09 10:33
 **/
@Slf4j
@Service
public class AdvertiserServiceImpl implements AdvertiserService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private AdvertiserMapper advertiserMapper;

    //获取所有广告商列表
    @Override
    public Result allAdList() {
        try{
            List<AdTable> allAdList = advertiserMapper.selectAdTableAll();
            if(allAdList.size()>0){
                Map<String ,Object> result = new HashMap<String ,Object>();
                result.put("total",allAdList.size());
                result.put("allAdList",allAdList);
                return Result.ok(result);
            }else {
                return Result.error("无广告商信息");
            }
        }catch (Exception e){
            log.error("查询所有广告商列表异常");
            e.printStackTrace();
            return Result.error("数据获取异常");
        }
    }

    //分页获取广告商列表
    @Override
    public Result pageAdList(int page){
        try{
            PageHelper.startPage(page,10);
            PageHelper.clearPage();
            List<AdTable> allAdList = advertiserMapper.selectAdTableAll();
            if (allAdList.size()>0){
                PageInfo<AdTable> adTablePageInfo = new PageInfo<>(allAdList);
                int startIndex =(page-1)*10;
                if(startIndex<allAdList.size()){
                    if(startIndex+10<=allAdList.size()){
                        adTablePageInfo.setList(allAdList.subList(startIndex,startIndex+10));
                    }else{
                        adTablePageInfo.setList(allAdList.subList(startIndex,allAdList.size()));
                    }
                }else{
                    adTablePageInfo.setList(null);
                }
                adTablePageInfo.setTotal(allAdList.size());
                return Result.ok(adTablePageInfo);
            }else{
                return Result.error("无数据");
            }
        }catch (Exception e){
            log.error("分页查询异常");
            e.printStackTrace();
            return Result.error("分页查询异常");
        }
    }

    @Override
    public Result updateAdStatus(int id, int status) {
        try{
            String currentTime = DateUtil.currentTime();
            AdUpdatePam adUpdatePam = new AdUpdatePam();
            adUpdatePam.setId(id);
            adUpdatePam.setStatus(status);
            adUpdatePam.setUpdateTime(currentTime);
            int i = advertiserMapper.updateAdStatus(adUpdatePam);
            AdTable adTable = advertiserMapper.selectAdTableById(id);
            String key = "AD::"+ adTable.getPackageName();
            redisTemplate.delete(key);
            if(i>0){
                return Result.ok();
            }else{
                return Result.error("无效操作");
            }
        }catch (Exception e){
            log.error("修改广告商状态异常");
            e.printStackTrace();
            return Result.error("修改广告商状态异常");
        }
    }

    //根据广告商id获取广告商信息
    @Override
    public Result getAdById(Integer id) {
        AdTable adTable = advertiserMapper.selectAdTableById(id);
        if (!adTable.equals(null)){
            return Result.ok(adTable);
        }else{
            return Result.error("无此广告商信息");
        }
    }

    //添加广告商
    @Override
    @Transactional
    public Result addAd(InsAdTablePam insAdTablePam) {
        try{
            String currentTime = DateUtil.currentTime();
            insAdTablePam.setStatus(0);
            insAdTablePam.setCreateTime(currentTime);
            insAdTablePam.setUpdateTime(currentTime);
            int i = advertiserMapper.insertAd(insAdTablePam);

            //创建表
            String packageName =insAdTablePam.getPackageName().replace('.','_');
            String tableName = packageName +"_"+insAdTablePam.getAdType();
            int j = advertiserMapper.createRecordTable(tableName);
            String key = "AD::"+ insAdTablePam.getPackageName();
//        SetOperations<String,Object> setOperations =redisTemplate.opsForSet();
//        setOperations.add(key, JSONObject.parse(insAdTablePam.toString()));
            redisTemplate.opsForValue().set(key ,JSONObject.parse(insAdTablePam.toString()));
            if(i>0){
                return Result.ok();
            }
            return Result.error("添加失败");
        }catch (Exception e){
            e.printStackTrace();
            log.error("添加异常");
            return Result.error("添加异常");
        }
    }

    //修改广告商
    @Override
    public Result updateAd(AdUpdatePam adUpdatePam) {
        try{
            String currentTime = DateUtil.currentTime();
            adUpdatePam.setUpdateTime(currentTime);
            int i = advertiserMapper.updateAdById(adUpdatePam);
            AdTable adTable = advertiserMapper.selectAdTableById(adUpdatePam.getId());
            String key = "AD::"+ adTable.getPackageName();
//        SetOperations<String,Object> setOperations =redisTemplate.opsForSet();
//        Object object= JSONObject.parse(adTable.toString());
//        System.out.println(object);
//        setOperations.add(key, JSONObject.parse(adTable.toString()));
            redisTemplate.opsForValue().set(key ,JSONObject.parse(adTable.toString()));

            if(i>0){
                return Result.ok();
            }
            return Result.error("修改失败");
        }catch (Exception e){
            e.printStackTrace();
            log.error("修改失败");
            return Result.error("修改失败");
        }
    }

    //删除广告商
    @Override
    public Result delAdById(Integer id) {
        try{
            AdTable adTable=advertiserMapper.selectAdTableById(id);
            int i = advertiserMapper.deleteAdById(id);
            String key = "AD::"+ adTable.getPackageName();
            redisTemplate.delete(key);
            if(i>0){
                return Result.ok();
            }
            return Result.error("删除失败");
        }catch (Exception e){
            log.error("删除广告商异常");
            e.printStackTrace();
            return Result.error("删除广告商异常");
        }
    }

    //搜索广告
    @Override
    public Result searchAd(String packageName, String appName) {
        try{
            List<AdTable> searchedAdList = advertiserMapper.searchAd(packageName,appName);
            if(searchedAdList.size()>0){
                return  Result.ok(searchedAdList);
            }else{
                return Result.ok("无相关数据",searchedAdList);
            }
        }catch (Exception e){
            e.printStackTrace();
            log.error("搜索广告失败");
            return Result.error("搜索广告失败");
        }
    }

    //根据包名查询广告参数
    @Override
    public Result getIndexByPackageName(String packageName) {
        try{
            List<AdTable> adTableList = advertiserMapper.getIndexByPackageName(packageName);
            if(adTableList.size()>0){
                return Result.ok(adTableList.subList(0,1));
            }else {
                return Result.error("无数据");
            }
        }catch (Exception e){
            e.printStackTrace();
            log.error("查询异常");
            return Result.error("查询异常");
        }
    }

    //根据广告类型查询广告参数
    @Override
    public Result getIndexByAdType(String adType) {
        try{
            AdTable adTable = advertiserMapper.getIndexByAdType(adType);
            if(adTable!=null){
                return Result.ok(adTable);
            }else {
                return Result.error("无数据");
            }
        }catch (Exception e){
            e.printStackTrace();
            log.error("查询异常");
            return Result.error("查询异常");
        }
    }

    //查询所有包名
    @Override
    public Result searchAllPackage() {
        try{
            List<String> packageNameList = advertiserMapper.searchAllPackage();
            if(packageNameList.size()>0){
                return Result.ok(packageNameList);
            }else{
                return Result.error("无数据");
            }
        }catch (Exception e){
            e.printStackTrace();
            log.error("搜索异常");
            return Result.error("搜索异常");
        }
    }

    //根据包名查询广告列表
    @Override
    public Result getAdListByPackageName(String packageName) {
        try{
            List<AdTable> adList = advertiserMapper.getIndexByPackageName(packageName);
            if(adList.size()>0){
                return Result.ok(adList);
            }else{
                return Result.error("无数据");
            }
        }catch (Exception e){
            e.printStackTrace();
            log.error("搜索异常");
            return Result.error("搜索异常");
        }
    }
}
