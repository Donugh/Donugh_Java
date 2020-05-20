package com.dangdang.usercontroller.service.impl;

import com.dangdang.usercontroller.entity.AdAppTable;
import com.dangdang.usercontroller.entity.Merchant;
import com.dangdang.usercontroller.entity.MerchantConf;
import com.dangdang.usercontroller.entity.Pam.InsAdAppTablePam;
import com.dangdang.usercontroller.entity.Pam.InsMerchantPam;
import com.dangdang.usercontroller.entity.Pam.MerchantAdUpdatePam;
import com.dangdang.usercontroller.entity.Pam.RanDomAdPam;
import com.dangdang.usercontroller.entity.Rtn.MerchantAdRtn;
import com.dangdang.usercontroller.entity.Rtn.MerchantConfRtn;
import com.dangdang.usercontroller.entity.Rtn.RandomAdRtn;
import com.dangdang.usercontroller.mapper.AdAllocationMapper;
import com.dangdang.usercontroller.service.AdAllocationService;
import com.dangdang.usercontroller.utils.DateUtil;
import com.dangdang.usercontroller.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author songxuan
 * @Create 2020-04-28 10:33
 **/
@Slf4j
@Service
public class AdAllocationServiceImpl implements AdAllocationService{

    @Autowired
    private AdAllocationMapper adAllocationMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    CacheManager cacheManager;

    /**
     * 获取随机广告
     * @param ranDomAdPam
     * @return key = "#ranDomAdPam.appNum"
     */
//    @Cacheable(cacheNames = {"AAA"},key = "#ranDomAdPam.appNum")
    @Override
    public Result randomAd(RanDomAdPam ranDomAdPam) {
        List<RandomAdRtn> list;
        int count = 0;
        try {
            String key = "AD::"+ranDomAdPam.getPackageName();
            Object aa = redisTemplate.opsForValue().get(key);
            list = (List<RandomAdRtn>)aa;
            if(list == null){
                list = adAllocationMapper.selAppAd(ranDomAdPam);
                redisTemplate.opsForValue().set(key,list);
            }
            Random random = new Random();
            int n = random.nextInt(list.size());
            RandomAdRtn randomAdRtn = list.get(n);
            return Result.ok(randomAdRtn);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取随机广告异常");
            return Result.error("数据查询异常");
        }
    }

    //获取所有广告app列表
    @Override
    public Result getAppTableAll() {
        try {
            List<AdAppTable> adAppTables = adAllocationMapper.selectAppTableAll();
            return Result.ok(adAppTables);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取app列表数据异常");
            return Result.error("数据获取失败");
        }
    }

    // 添加广告app
    @Override
    public Result insAppTable(InsAdAppTablePam adAppTablePam) {
        try {
            adAppTablePam.setCreateTime(DateUtil.currentTime());
            int i = adAllocationMapper.insAdAppTable(adAppTablePam);
            if(i > 0){
               return Result.ok();
            }else{
               return Result.error("数据插入异常");
            }
        } catch (Exception e) {
            log.error("新增广告app数据异常");
            e.printStackTrace();
            return Result.error("数据插入异常");
        }
    }


    // 获取商户列表
    @Override
    public Result getMerchant() {
        try {
            List<Merchant> merchants = adAllocationMapper.selectMcAll();
            return Result.ok(merchants);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取商户列表数据异常");
            return Result.error("数据获取失败");
        }
    }


    // 新增商户信息
    @Override
    public Result insMerchant(InsMerchantPam merchantPam) {
        try {
            merchantPam.setCreateTime(DateUtil.currentTime());
            int i = adAllocationMapper.insMerchant(merchantPam);
            if(i > 0){
                return Result.ok();
            }else{
                return Result.error("插入数据异常");
            }
        } catch (Exception e) {
            log.error("新增商户信息异常");
            e.printStackTrace();
            return Result.error("插入数据异常");
        }
    }


    // 根据商户号获取商户广告配置
    @Override
    public Result getMerchantConf(String merchantId) {
        List<MerchantConfRtn> merchantConfRtns = new ArrayList<>();
        try {
            List<MerchantConf> publicities = adAllocationMapper.merchantConf(merchantId);
            if(publicities.size() < 1){
                return Result.ok();
            }
            for (MerchantConf publicity : publicities) {
                MerchantConfRtn merchantConfRtn = new MerchantConfRtn();
                merchantConfRtn.setId(publicity.getId());
                merchantConfRtn.setAppName(publicity.getAppName());
                merchantConfRtn.setStatus(publicity.getStatus());
                merchantConfRtn.setAstrict(publicity.getAstrict());
                merchantConfRtn.setAwardMoney(publicity.getAwardMoney());
                merchantConfRtn.setAwardMoney(publicity.getAwardMoney());
                merchantConfRtn.setCreateTime(publicity.getCreateTime());
                merchantConfRtns.add(merchantConfRtn);
            }
            return Result.ok(merchantConfRtns);
        } catch (Exception e) {
            log.error("获取商户广告配置异常，商户号："+merchantId);
            e.printStackTrace();
            return Result.error("数据获取异常");
        }
    }


    // 获取商户广告返回
    @Override
    public Result getMerchantAd(String merchantNum) {
        try {
            MerchantAdRtn merchantAdRtn = new MerchantAdRtn();
            List<AdAppTable> adAppTables = adAllocationMapper.selectAppAdPack(merchantNum);
            if(adAppTables.size() < 1){
                return Result.ok(merchantAdRtn);
            }
            Random random = new Random();
            int n = random.nextInt(adAppTables.size());
            AdAppTable adAppTable = adAppTables.get(n);
            merchantAdRtn.setPackageName(adAppTable.getPackageName());
            return Result.ok(merchantAdRtn);
        } catch (Exception e) {
            log.error("根据商户号查询广告异常，商户号："+merchantNum);
            e.printStackTrace();
            return Result.error("获取商户广告异常");
        }
    }


    // 修改用户广告显示状态
    @Override
    public Result updateMerchantConf(MerchantAdUpdatePam updatePam) {
        try {
            int i = adAllocationMapper.updateMerchantSt(updatePam);
            if(i == 1){
                return Result.ok();
            }else {
                return Result.error("无效的操作");
            }
        } catch (Exception e) {
            log.error("修改用户广告状态异常");
            e.printStackTrace();
            return Result.error("数据操作异常");
        }
    }


    // 删除商户
    @Override
    public Result delMerchant(String mcId) {
        try {
            int i = adAllocationMapper.delMerchantById(mcId);
            if(i > 0){
                return Result.ok();
            }else{
                return Result.error("无效的操作");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("数据操作异常");
        }
    }


    // 删除广告
    @Override
    public Result delAd(String appId) {
        try {
            int i = adAllocationMapper.delAppTabById(appId);
            if(i > 0){
                return Result.ok();
            }else{
                return Result.error("无效的操作");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("数据操作异常");
        }
    }
}
