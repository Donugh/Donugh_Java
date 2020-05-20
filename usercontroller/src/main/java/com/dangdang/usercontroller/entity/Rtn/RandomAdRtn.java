package com.dangdang.usercontroller.entity.Rtn;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author songxuan
 * @Create 2020-05-06 10:12
 **/
@Data
public class RandomAdRtn implements Serializable{
    private String adType;
    private String adId;
    private String appId;
    private String placeTag;
}
