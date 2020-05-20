package com.dangdang.usercontroller.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable{
    private int id;
    // 用户名
    private String userName;
    // 密码
    private String password;
    // 创建时间
    private String createTime;
    // 可用状态
    private int enabled;

}
