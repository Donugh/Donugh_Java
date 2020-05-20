package com.dangdang.usercontroller.entity;

import lombok.Data;

/**
 * @Author songxuan
 * @Create 2020-04-22 16:30
 **/
@Data
public class MerchantConf {
    private int id;
    //商户id
    private String merchantId;
    // 广告appId
    private String appId;
    // app名称
    private String appName;
    // 开启状态
    private int status;
    // 限制次数
    private int astrict;
    // 是否开启奖励 0开启1关闭
    private int awardStatus;
    // 奖励金额
    private double awardMoney;
    // 创建时间
    private String createTime;
    // 可用状态
    private int enabled;
}
