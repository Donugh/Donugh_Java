package com.dangdang.usercontroller.mapper;

import com.dangdang.usercontroller.entity.AppTable;
import com.dangdang.usercontroller.entity.MerchantConf;
import com.dangdang.usercontroller.entity.Pam.AppUpdatePam;
import com.dangdang.usercontroller.entity.Pam.InsAppTablePam;
import com.dangdang.usercontroller.entity.Rtn.AppMerchantRtn;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 应用mapper
 * @Author HuDong
 * @Create 2020-05-10 10:45
 **/
public interface AppMapper {

    //有id时获取所有应用列表
    @Options(flushCache = Options.FlushCachePolicy.TRUE)
    @Select("select app.id,app.app_id,app.app_name,app.app_key,app.package_name,app.update_time," +
            "app.create_teme,app.enabled,mrh.merchant_name,mrh.merchant_num \n" +
            "from app_table as app left join merchant_conf as mrhcnf on app.app_id = mrhcnf.app_id " +
            "left join merchant as mrh on mrh.merchant_num = mrhcnf.merchant_id " +
            "where mrh.id=#{id};" )
    List<AppMerchantRtn> selectAppTableAll(@Param("id") int id);

    //无id时获取所有应用列表
    @Options(flushCache = Options.FlushCachePolicy.TRUE)
    @Select("select app.id,app.app_id,app.app_name,app.app_key,app.package_name,app.update_time," +
            "app.create_teme,app.enabled,mrh.merchant_name,mrh.merchant_num \n" +
            "from app_table as app left join merchant_conf as mrhcnf on app.app_id = mrhcnf.app_id " +
            "left join merchant as mrh on mrh.merchant_num = mrhcnf.merchant_id ")
    List<AppMerchantRtn> selectAppTableAllWithoutId();

    //修改应用状态
    @Update("update app_table set enabled = #{enabled},update_time = #{updateTime} where id = #{id}")
    int updateAppStatus(AppUpdatePam AppUpdatePam);

    // 根据Id获取应用信息
    @Select("select * from app_table where id = #{id}")
    AppTable selectAppTableById(Integer id);

    //根据Id删除广告信息
    @Delete("delete from app_table where id = #{id}")
    int deleteAppById(Integer id);

    // 新增应用信息
    @Insert("insert into app_table(app_id,app_name,app_key,package_name,create_teme,update_time,enabled) " +
            "value(#{appId},#{appName},#{appKey},#{packageName},#{createTime},#{updateTime},#{enabled});")
    int insertApp(InsAppTablePam insAppPam);

    //新增应用、商户关联数据
    @Insert("INSERT INTO merchant_conf(merchant_id,app_id,app_name, status, astrict, award_status, award_money, enabled) " +
            "VALUES (#{merchantId}, #{appId}, #{appName}, #{status}, #{astrict}, #{awardStatus}, #{awardMoney}, #{enabled});")
    int insertMerchantConfig(MerchantConf   merchantConf);

    // 修改应用信息
    @Update("update app_table set package_name = #{packageName},app_name = #{appName},app_key = #{appKey},update_time = #{updateTime} where id = #{id}")
    int updateAppById(AppUpdatePam AppUpdatePam);

    // 删除应用
    @Delete("delete from app_table where id = #{id}")
    int delAppById(Integer id);

    //删除应用、商户关联数据
    @Delete("delete from merchant_conf where app_id = #{appId}")
    int deleteMerchantConf(String appId);
}
