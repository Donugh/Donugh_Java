package com.dangdang.usercontroller.mapper;

import com.dangdang.usercontroller.entity.Merchant;
import com.dangdang.usercontroller.entity.Pam.InsMerchantPam;
import com.dangdang.usercontroller.entity.Pam.MerchantUpdatePam;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 商户mapper
 * @Author HuDong
 * @Create 2020-05-09 10:45
 **/
public interface MerchantMapper {

    //获取所有商户列表
    @Select("select * from merchant")
    @Options(flushCache = Options.FlushCachePolicy.TRUE)
    List<Merchant> selectMerchantTableAll();

    //修改商户状态
    @Update("update merchant set enabled = #{enabled},update_time = #{updateTime} where id = #{id}")
    int updateMerchantStatus(MerchantUpdatePam MerchantUpdatePam);

    // 根据Id获取商户信息
    @Select("select * from merchant where id = #{id}")
    Merchant selectMerchantTableById(Integer id);

    //根据Id删除商户信息
    @Delete("delete from merchant where id = #{id}")
    int deleteMerchantById(Integer id);

    // 新增商户信息
    @Insert("insert into merchant(merchant_num,merchant_name,create_time,update_time,enabled) value(#{merchantNum},#{merchantName},#{createTime},#{updateTime},#{enabled})")
    int insertMerchant(InsMerchantPam insMerchantPam);

    // 修改商户信息
    @Update("update merchant set merchant_num = #{merchantNum},merchant_name = #{merchantName},enabled = #{enabled},update_time = #{updateTime} where id = #{id}")
    int updateMerchantById(MerchantUpdatePam MerchantUpdatePam);

//    // 删除商户
//    @Update("update Merchant_table set status = 1 ,update_time = #{updateTime} where id = #{id}")
//    int delMerchantById(Integer id,String updateTime);

}
