package com.dangdang.usercontroller.entity.Rtn;

import lombok.Data;

/**
 * @Author songxuan
 * @Create 2020-04-28 11:38
 **/
@Data
public class MerchantConfRtn {
    private int id;
    // 广告app名称
    private String appName;
    // 是否启用
    private int status;
    // 限制条数
    private int astrict;
    // 是否开启奖励
    private int awardStatus;
    // 奖励金额
    private double awardMoney;
    // 创建时间
    private String createTime;
}
