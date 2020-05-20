package com.dangdang.usercontroller.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Record {
    private int id;
    private String systemLanguage;
//    private String systemVersion;
//    private String systemModel;
//    private String deviceBrand;
    private String imei;
    private String uniqueId;
    private String buildVersionSdk;
    private String buildType;
    private String hardware;
    private String buildUser;
    private String buildVersionRelease;
    private String productDevice;
    private String buildVersionCodeName;
    private String buildTags;
    private String productManufacturer;
    private String productModel;
    private String buildFingerprint;
    private String productBrand;
    private String productName;
    private String buildVersionIncremental;
    private String buildHost;
    private String buildId;
    private String buildDateUtc;
    private String buildDisplayId;
    private String serialno;
    private String productBoard;
    private String userId;
    private String upId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date clickTime;

    //是否为默认表的数据；
    private int isDefault;
}
