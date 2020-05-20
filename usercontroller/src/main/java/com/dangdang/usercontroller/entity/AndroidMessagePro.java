package com.dangdang.usercontroller.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @Author songxuan
 * @Create 2020-05-07 14:07
 **/
@Data
public class AndroidMessagePro {
    //当前手机系统语言
    @JsonProperty(value="systemLanguage")
    private String systemLanguage;
    //当前手机系统版本号
//    @JsonProperty(value="systemVersion")
//    private String systemVersion;
//    //手机型号
//    @JsonProperty(value="systemModel")
//    private String systemModel;
//    //手机厂商
//    @JsonProperty(value="deviceBrand")
//    private String deviceBrand;
    //手机imei
    @JsonProperty(value="imei")
    private String imei;  // 可能为null
    //手机unqueId
    @JsonProperty(value="uniqueID")
    private String uniqueID; // 可能为null
    //BuildeVersion Sdk版本信息
    @JsonProperty(value="BuildVersionSdk")
    private String BuildVersionSdk;
    //BuildType
    @JsonProperty(value="BuildType")
    private String BuildType;
    //Hardware
    @JsonProperty(value="Hardware")
    private String Hardware;
    //BuildUser
    @JsonProperty(value="BuildUser")
    private String BuildUser;
    //BuildVersionRelease
    @JsonProperty(value="BuildVersionRelease")
    private String BuildVersionRelease;
    //ProductDevice
    @JsonProperty(value="ProductDevice")
    private String ProductDevice;
    //BuildVersionCodename
    @JsonProperty(value="BuildVersionCodename")
    private String BuildVersionCodename;
    //BuildTags
    @JsonProperty(value="BuildTags")
    private String BuildTags;
    //product.manufacturer
    @JsonProperty(value="ProductManufacturer")
    private String ProductManufacturer;
    //product.model
    @JsonProperty(value="ProductModel")
    private String ProductModel;
    //build.fingerprint
    @JsonProperty(value="BuildFingerprint")
    private String BuildFingerprint;
    //product.brand
    @JsonProperty(value="ProductBrand")
    private String ProductBrand;
    //product.name
    @JsonProperty(value="ProductName")
    private String ProductName;
    //build.version.incremental0
    @JsonProperty(value="BuildVersionIncremental")
    private String BuildVersionIncremental;
    //build.host
    @JsonProperty(value="BuildHost")
    private String BuildHost;
    //build.id
    @JsonProperty(value="BuildId")
    private String BuildId;
    //build.date.utc
    @JsonProperty(value="BuildDateUtc")
    private String BuildDateUtc;
    //build.display.id
    @JsonProperty(value="BuildDisplayId")
    private String BuildDisplayId;
    //serialno
    @JsonProperty(value="Serialno")
    private String Serialno;
    //product.board
    @JsonProperty(value="ProductBoard")
    private String ProductBoard;
    @JsonProperty(value="ClickTime")
    private String ClickTime;

    @JsonProperty(value="UserId")
    private String UserId;
    @JsonProperty(value="UpId")
    private String UpId;
    //包名
    @JsonProperty(value="packageName")
    private  String packageName;
    //广告类型
    @JsonProperty(value="adType")
    private String adType;

}
