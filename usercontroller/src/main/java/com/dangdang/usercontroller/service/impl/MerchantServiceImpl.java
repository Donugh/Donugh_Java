package com.dangdang.usercontroller.service.impl;


import com.dangdang.usercontroller.entity.Merchant;
import com.dangdang.usercontroller.entity.Pam.InsMerchantPam;
import com.dangdang.usercontroller.entity.Pam.MerchantUpdatePam;
import com.dangdang.usercontroller.mapper.MerchantMapper;
import com.dangdang.usercontroller.service.MerchantService;
import com.dangdang.usercontroller.utils.DateUtil;
import com.dangdang.usercontroller.utils.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author HuDong
 * @Create 2020-05-09 10:34
 **/
@Slf4j
@Service
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private MerchantMapper merchantMapper;

    //获取所有广告商列表
    @Override
    public Result allMerchantList() {
        try{
            List<Merchant> allMerchantList = merchantMapper.selectMerchantTableAll();
            if(allMerchantList.size()>0){
                return Result.ok(allMerchantList);
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
    public Result pageMerchantList(int page){
        try{
            PageHelper.startPage(page,10);
            PageHelper.clearPage();
            List<Merchant> allMerchantList = merchantMapper.selectMerchantTableAll();
            if (allMerchantList.size()>0){
                List<Merchant> pageList = allMerchantList;
                PageInfo<Merchant> MerchantTablePageInfo = new PageInfo<>(pageList);
                int startIndex =(page-1)*10;
                if(startIndex<pageList.size()){
                    if(startIndex+10<=pageList.size()){
                        MerchantTablePageInfo.setList(pageList.subList(startIndex,startIndex+10));
                    }else{
                        MerchantTablePageInfo.setList(pageList.subList(startIndex,allMerchantList.size()));
                    }
                }else {
                    MerchantTablePageInfo.setList(null);
                }
                MerchantTablePageInfo.setTotal(pageList.size());
                return Result.ok(MerchantTablePageInfo);
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
    public Result updateMerchantStatus(int id, int enable) {
        try{
            String currentTime = DateUtil.currentTime();
            MerchantUpdatePam MerchantUpdatePam = new MerchantUpdatePam();
            MerchantUpdatePam.setId(id);
            MerchantUpdatePam.setEnabled(enable);
            MerchantUpdatePam.setUpdateTime(currentTime);
            int i = merchantMapper.updateMerchantStatus(MerchantUpdatePam);
            Merchant merchant = merchantMapper.selectMerchantTableById(id);
            String key = "Merchant::"+ merchant.getMerchantNum();
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
    public Result getMerchantById(Integer id) {
        Merchant merchant = merchantMapper.selectMerchantTableById(id);
        if (!merchant.equals(null)){
            return Result.ok(merchant);
        }else{
            return Result.error("无此广告商信息");
        }
    }

    //添加广告商
    @Override
    public Result addMerchant(InsMerchantPam insMerchantPam) {
        String currentTime = DateUtil.currentTime();
        insMerchantPam.setEnabled(0);
        insMerchantPam.setCreateTime(currentTime);
        insMerchantPam.setUpdateTime(currentTime);
        int i = merchantMapper.insertMerchant(insMerchantPam);
//        String key = "Merchant::"+ insMerchantPam.getMerchantNum();
//        SetOperations<String,Object> setOperations =redisTemplate.opsForSet();
//        setOperations.add(key, JSONObject.parse(insMerchantTablePam.toString()));
//        redisTemplate.opsForValue().set(key ,JSONObject.parse(insMerchantPam.toString()));
        if(i>0){
            return Result.ok();
        }
        return Result.error("添加失败");
    }

    //修改广告商
    @Override
    public Result updateMerchant(MerchantUpdatePam merchantUpdatePam) {
        String currentTime = DateUtil.currentTime();
        merchantUpdatePam.setUpdateTime(currentTime);
        int i = merchantMapper.updateMerchantById(merchantUpdatePam);
        Merchant merchant = merchantMapper.selectMerchantTableById(merchantUpdatePam.getId());
        String key = "Merchant::"+ merchant.getMerchantNum();
//        SetOperations<String,Object> setOperations =redisTemplate.opsForSet();
//        Object object= JSONObject.parse(MerchantTable.toString());
//        System.out.println(object);
//        setOperations.Merchant(key, JSONObject.parse(MerchantTable.toString()));
//        redisTemplate.opsForValue().set(key ,JSONObject.parse(merchant.toString()));

        if(i>0){
            return Result.ok();
        }
        return Result.error("修改失败");
    }

    //删除广告商
    @Override
    public Result delMerchantById(Integer id) {
        try{
            int i = merchantMapper.deleteMerchantById(id);
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
}
