package com.dangdang.usercontroller.entity;

import lombok.Data;

/**
 * @Author songxuan
 * @Create 2020-05-06 9:50
 **/
@Data
public class AdTable {
    private int id;
    // app编号
    private String packageName;
    // App名称
    private String appName;
    // 广告请求返回参数
    private String adType;
    // 广告请求返回参数
    private String typeName;
    // 广告请求返回参数
    private String adId;
    // 广告请求返回参数
    private String appId;
    // 广告请求返回参数
    private String placeTag;
    // 开启状态0开启1关闭
    private int status;
    // 更新时间
    private String updateTime;
    // 创建时间
    private String createTime;

    @Override
    public String toString() {
        return  "{\"id\":\"" + id + "\"," +
                "\"packageName\":\"" + packageName + "\" ," +
                "\" appName\":\"" + appName + "\" ,"+
                "\"adType\" :\"" + adType + "\" ,"+
                "\" typeName\" :\""+ typeName + "\" ,"+
                "\" adId\" :\"" + adId + "\" ,"+
                "\" appId\" :\""+ appId + "\" ,"+
                "\" placeTag\" :\""+ placeTag +"\" ,"+
                "\" status\" :\"" + status + "\" ,"+
                "\" updateTime\" :\"" + updateTime + "\" ,"+
                "\" createTime\" :\"" + createTime + "\"}";

    }
}
