package com.dangdang.usercontroller.mapper;

import com.dangdang.usercontroller.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    // 用户登录
    @Select("select * from user_login where user_name = #{name} and password = #{password} and enabled = 0")
    User userLogin(@Param("name") String name,@Param("password")  String password);
}
