package com.dangdang.usercontroller.service.impl;


import com.dangdang.usercontroller.entity.AppTable;
import com.dangdang.usercontroller.entity.MerchantConf;
import com.dangdang.usercontroller.entity.Pam.AppUpdatePam;
import com.dangdang.usercontroller.entity.Pam.InsAppTablePam;
import com.dangdang.usercontroller.entity.Rtn.AppMerchantRtn;
import com.dangdang.usercontroller.mapper.AppMapper;
import com.dangdang.usercontroller.service.AppService;
import com.dangdang.usercontroller.utils.DateUtil;
import com.dangdang.usercontroller.utils.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author HuDong
 * @Create 2020-05-10 10:33
 **/
@Slf4j
@Service
public class AppServiceImpl implements AppService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private AppMapper appMapper;

    //获取所有App列表
    @Override
    public Result allAppList(String id) {
        List<AppMerchantRtn> allAppList=new ArrayList<>();
        try{
            if(!id.equals("")&&!id.equals(null)){
                allAppList = appMapper.selectAppTableAll(Integer.parseInt(id));
            }else{
                allAppList = appMapper.selectAppTableAllWithoutId();
            }
            if(allAppList.size()>0){
                return Result.ok(allAppList);
            }else {
                return Result.error("无App信息");
            }
        }catch (Exception e){
            log.error("查询所有App列表异常");
            e.printStackTrace();
            return Result.error("数据获取异常");
        }

    }

    //分页获取App列表
    @Override
    public Result pageAppList(int page,String id){
        List<AppMerchantRtn> allAppList=new ArrayList<>();
        PageHelper.startPage(page,10);
        PageHelper.clearPage();
        try{
            if(!id.equals("")&&!id.equals(null)){
                allAppList = appMapper.selectAppTableAll(Integer.parseInt(id));
            }else{
                allAppList = appMapper.selectAppTableAllWithoutId();
            }
            if (allAppList.size()>0){
                PageInfo<AppMerchantRtn> appTablePageInfo = new PageInfo<>(allAppList);
                int startIndex =(page-1)*10;
                if(startIndex+10<=allAppList.size()){
                    appTablePageInfo.setList(allAppList.subList(startIndex,startIndex+10));
                }else{
                    appTablePageInfo.setList(allAppList.subList(startIndex,allAppList.size()));
                }
                appTablePageInfo.setTotal(allAppList.size());
                return Result.ok(appTablePageInfo);
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
    public Result updateAppStatus(int id, int enabled) {
        try{
            String currentTime = DateUtil.currentTime();
            AppUpdatePam appUpdatePam = new AppUpdatePam();
            appUpdatePam.setId(id);
            appUpdatePam.setEnabled(enabled);
            appUpdatePam.setUpdateTime(currentTime);
            int i = appMapper.updateAppStatus(appUpdatePam);
            AppTable appTable = appMapper.selectAppTableById(id);
            String key = "APP::"+ appTable.getPackageName();
            redisTemplate.delete(key);
            if(i>0){
                return Result.ok();
            }else{
                return Result.error("无效操作");
            }
        }catch (Exception e){
            log.error("修改App状态异常");
            e.printStackTrace();
            return Result.error("修改App状态异常");
        }
    }

    //根据AppId获取App信息
    @Override
    public Result getAppById(Integer id) {
        try{
            AppTable appTable = appMapper.selectAppTableById(id);
            if (!appTable.equals(null)){
                return Result.ok(appTable);
            }else{
                return Result.error("无此App信息");
            }
        }catch (Exception e){
            log.error("获取App状态异常");
            e.printStackTrace();
            return Result.error("获取App状态异常");
        }
    }

    //添加App
    @Override
    public Result addApp(InsAppTablePam insAppTablePam) {
        try{
            String currentTime = DateUtil.currentTime();
            insAppTablePam.setEnabled(0);
            insAppTablePam.setCreateTime(currentTime);
            insAppTablePam.setUpdateTime(currentTime);
            int i = appMapper.insertApp(insAppTablePam);

            //添加商户、应用关联数据
            MerchantConf merchantConf = new MerchantConf();
            merchantConf.setAppId(insAppTablePam.getAppId());
            merchantConf.setAppName(insAppTablePam.getAppName());
            merchantConf.setMerchantId(insAppTablePam.getMerchantNum());
            merchantConf.setStatus(0);
            merchantConf.setAstrict(0);
            merchantConf.setAwardStatus(1);
            merchantConf.setAwardMoney(0);
            merchantConf.setCreateTime(currentTime);
            merchantConf.setStatus(0);
            int j = appMapper.insertMerchantConfig(merchantConf);

            String key = "AD::"+ insAppTablePam.getPackageName();
//        SetOperations<String,Object> setOperations =redisTemplate.opsForSet();
//        setOperations.add(key, JSONObject.parse(insAdTablePam.toString()));
//        redisTemplate.opsForValue().set(key ,JSONObject.parse(insAdTablePam.toString()));
            if(i>0 && j>0){
                return Result.ok();
            }
            return Result.error("添加失败");
        }catch (Exception e){
            e.printStackTrace();
            log.error("添加应用失败");
            return Result.error("添加失败");
        }

    }

    //修改App
    @Override
    public Result updateApp(AppUpdatePam appUpdatePam) {
        try{
            String currentTime = DateUtil.currentTime();
            appUpdatePam.setUpdateTime(currentTime);
            int i = appMapper.updateAppById(appUpdatePam);
            AppTable appTable = appMapper.selectAppTableById(appUpdatePam.getId());
            String key = "APP::"+ appTable.getPackageName();
//        SetOperations<String,Object> setOperations =redisTemplate.opsForSet();
//        Object object= JSONObject.parse(adTable.toString());
//        System.out.println(object);
//        setOperations.add(key, JSONObject.parse(adTable.toString()));
//        redisTemplate.opsForValue().set(key ,JSONObject.parse(adTable.toString()));
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

    //删除App
    @Override
    public Result delAppById(Integer id) {
        try{
            //删除app表数据
            AppTable appTable = appMapper.selectAppTableById(id);
            int i = appMapper.deleteAppById(id);
            //删除商户、app关联数据
            //int j =appMapper.deleteMerchantConf(appTable.getAppId());
            if(i>0){
                return Result.ok();
            }
            return Result.error("删除失败");
        }catch (Exception e){
            log.error("删除App异常");
            e.printStackTrace();
            return Result.error("删除App异常");
        }

    }
}
