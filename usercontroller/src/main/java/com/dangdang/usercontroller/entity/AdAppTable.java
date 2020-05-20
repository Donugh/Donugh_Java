package com.dangdang.usercontroller.entity;

import lombok.Data;

/**
 * 广告app列表
 * @Author songxuan
 * @Create 2020-04-28 10:02
 **/
@Data
public class AdAppTable {
    private int id;
    private String appId;
    private String appName;
    private String packageName;
    private String createTime;
    private int enabled;
}
