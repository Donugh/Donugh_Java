package com.dangdang.usercontroller.entity.Rtn;

import lombok.Data;

/**
 * app 实体类
 * @Author HuDong
 * @Create 2020-05-10 16:02
 **/
@Data
public class AppMerchantRtn {
    private int id;
    private String appId;
    private String merchantId;
    private String merchantName;
    private String appName;
    private String appKey;
    private String packageName;
    private String createTime;
    private String updateTime;
    private int enabled;
}
