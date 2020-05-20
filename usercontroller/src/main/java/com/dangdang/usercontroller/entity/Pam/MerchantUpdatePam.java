package com.dangdang.usercontroller.entity.Pam;

import lombok.Data;

/**
 * @Author HuDong
 * @Create 2020-05-09 10:15
 **/
@Data
public class MerchantUpdatePam {
    private int id;
    //商户名称
    private String merchantName;
    //商户编号
    private String merchantNum;
    // 更新时间
    private String updateTime;
    // 创建时间
    private String createTime;
    // 数据可用状态0可用
    private int enabled;
}
