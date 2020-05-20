package com.dangdang.usercontroller.entity.Pam;

import lombok.Data;

/**
 * @Author songxuan
 * @Author HuDong
 * @Create 2020-05-09 11:02
 **/
@Data
public class InsMerchantPam {
    // 商户编号
    private String merchantNum;
    // 商户名称
    private String merchantName;
    //创建时间
    private String createTime;
    //修改时间
    private String updateTime;
    // 数据可用状态0可用
    private int enabled;
}
