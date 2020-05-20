package com.dangdang.usercontroller.service;

import com.dangdang.usercontroller.entity.Pam.InsAdAppTablePam;
import com.dangdang.usercontroller.entity.Pam.InsMerchantPam;
import com.dangdang.usercontroller.entity.Pam.MerchantAdUpdatePam;
import com.dangdang.usercontroller.entity.Pam.RanDomAdPam;
import com.dangdang.usercontroller.utils.Result;

/**
 * @Author songxuan
 * @Create 2020-04-28 10:33
 **/
public interface AdAllocationService {

    Result randomAd(RanDomAdPam ranDomAdPam);

    // 获取所有 广告app列表
    Result getAppTableAll();

    // 添加广告app
    Result insAppTable(InsAdAppTablePam adAppTablePam);

    //获取商户列表
    Result getMerchant();

    // 添加商户
    Result insMerchant(InsMerchantPam merchantPam);

    // 获取商户广告配置
    Result getMerchantConf(String merchantNum);

    // 获取商户广告配置
    Result getMerchantAd(String merchantNum);

    // 修改商户广告配置
    Result updateMerchantConf(MerchantAdUpdatePam updatePam);

    // 删除商户
    Result delMerchant(String mcId);

    // 删除广告app
    Result delAd(String appId);
}
