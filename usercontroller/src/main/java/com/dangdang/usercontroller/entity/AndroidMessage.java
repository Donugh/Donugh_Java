package com.dangdang.usercontroller.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author songxuan
 * @Create 2020-04-22 9:44
 **/
@Data
public class AndroidMessage implements Serializable {
    /**
     * 获取手机唯一序列号--DeviceUUID
     * 获取手机序列号--DeviceId
     * 获取应用的ApplicationId--ApplicationId
     * 取得操作系统版本号--OSVersion
     * 获取应用版本号--AppVersion
     * 获取签名摘要--Sign
     * 返回用户手机运营商标识--ProvidersName
     * 网络类型判断--NetworkType
     * 获取当前手机系统版本号--SystemVersion
     * 判断当前设备是手机还是平板--isPad
     * 获取手机厂商--DeviceBrand
     */

    @JsonProperty(value="DeviceUUId")
    private String DeviceUUId;
    @JsonProperty(value="DeviceId")
    private String DeviceId;
    @JsonProperty(value="ApplicationId")
    private String ApplicationId;
    @JsonProperty(value="OSVersion")
    private String OSVersion;
    @JsonProperty(value="AppVersion")
    private String AppVersion;
    @JsonProperty(value="Sign")
    private String Sign;
    @JsonProperty(value="ProvidersName")
    private String ProvidersName;
    @JsonProperty(value="NetworkType")
    private String NetworkType;
    @JsonProperty(value="SystemVersion")
    private String SystemVersion;
    @JsonProperty(value="isPad")
    private int isPad;
    @JsonProperty(value="DeviceBrand")
    private String DeviceBrand;
    @JsonProperty(value="RequestTime")
    private String RequestTime;
    private String createTime;
}
