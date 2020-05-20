package com.dangdang.usercontroller.service;

import com.dangdang.usercontroller.entity.Pam.InsMerchantPam;
import com.dangdang.usercontroller.entity.Pam.MerchantUpdatePam;
import com.dangdang.usercontroller.utils.Result;

/**
 * @Author HuDong
 * @Create 2020-05-010 17:42
 **/
public interface MerchantService {

    //获取所有商户列表
    Result allMerchantList();

    //分页获取商户列表
    Result pageMerchantList(int page);

    //修改广告状态
    Result updateMerchantStatus(int id, int status);

    // 根据id获取商户信息
    Result getMerchantById(Integer id);

    // 添加商户
    Result addMerchant(InsMerchantPam insMerchantPam);

    //修改商户
    Result updateMerchant(MerchantUpdatePam MerchantUpdatePam);

    //删除商户
    Result delMerchantById(Integer id);
}
