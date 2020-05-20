package com.dangdang.usercontroller.entity;

import lombok.Data;

/**
 * 商户信息
 * @Author songxuan
 * @Create 2020-04-21 20:50
 **/
@Data
public class Merchant {
    private int id;
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
