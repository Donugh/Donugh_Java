package com.dangdang.usercontroller.mapper;

import com.dangdang.usercontroller.entity.AdTable;
import com.dangdang.usercontroller.entity.Pam.AdUpdatePam;
import com.dangdang.usercontroller.entity.Pam.InsAdTablePam;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 广告mapper
 * @Author HuDong
 * @Create 2020-05-06 16:45
 **/
public interface AdvertiserMapper {

    //获取所有广告商列表
    @Options(flushCache = Options.FlushCachePolicy.TRUE)
    @Select("select * from ad_table")
    List<AdTable> selectAdTableAll();

    //修改广告商状态
    @Update("update ad_table set status = #{status},update_time = #{updateTime} where id = #{id}")
    int updateAdStatus(AdUpdatePam adUpdatePam);

    // 根据Id获取广告商信息
    @Select("select * from ad_table where id = #{id}")
    AdTable selectAdTableById(Integer id);

    //根据Id删除广告信息
    @Delete("delete from ad_table where id = #{id}")
    int deleteAdById(Integer id);

    // 新增广告商信息
    @Insert("insert into ad_table(package_name,app_name,ad_type,type_name,ad_id,app_id,place_tag,status,update_time,create_time) " +
            "value(#{packageName},#{appName},#{adType},#{typeName},#{adId},#{appId},#{placeTag},#{status},#{updateTime},#{createTime})")
    int insertAd(InsAdTablePam insAdTablePam);

    // 修改广告商信息
    @Update("update ad_table set package_name = #{packageName},app_name = #{appName},ad_type = #{adType},type_name = #{typeName}," +
            "ad_id = #{adId},app_id = #{appId},place_tag = #{placeTag},status = #{status},update_time = #{updateTime},create_time = #{createTime} where id = #{id}")
    int updateAdById(AdUpdatePam adUpdatePam);

    //搜索广告信息
    @Select("<script>"+"select * from ad_table where 1 = 1 " +
            "<if test='packageName!=null'> and  package_name like concat('%', #{packageName} ,'%' ) </if>" +
            "<if test='appName!=null'> and app_name like concat('%', #{appName}, '%')</if>"+"</script>")
    List<AdTable> searchAd(@Param("packageName") String packageName,@Param("appName") String appName);

//    // 删除商户
//    @Update("update ad_table set status = 1 ,update_time = #{updateTime} where id = #{id}")
//    int delAdById(Integer id,String updateTime);

    //创建packageName、adType关联表
    @Update("CREATE TABLE ${tableName}(\n" +
            "          id bigint(40) NOT NULL AUTO_INCREMENT,\n" +
            "          system_language VARCHAR(30),\n" +
//            "          system_version VARCHAR(30),\n" +
//            "          system_model VARCHAR(30),\n" +
//            "          device_brand VARCHAR(30),\n" +
            "          imei VARCHAR(80),\n" +
            "          unique_id VARCHAR(230),\n" +
            "          build_version_sdk VARCHAR(30),\n" +
            "          build_type varchar(30),\n" +
            "          hardware varchar(60),\n" +
            "          build_user varchar(50),\n" +
            "          build_version_release varchar(40),\n" +
            "          product_device varchar(60),\n" +
            "          build_version_code_name varchar(50),\n" +
            "          build_tags varchar(30),\n" +
            "          product_manufacturer varchar(30),\n" +
            "          product_model varchar(30),\n" +
            "          build_fingerprint varchar(200),\n" +
            "          product_brand varchar(80),\n" +
            "          product_name varchar(80),\n" +
            "          build_version_Incremental varchar(80),\n" +
            "          build_host varchar(80),\n" +
            "          build_id varchar(80),\n" +
            "          build_date_utc varchar(80),\n" +
            "          build_display_id varchar(80),\n" +
            "          serialno varchar(80),\n" +
            "          product_board varchar(80),\n" +
            "          user_id varchar(80),\n" +
            "          up_id varchar(80),\n" +
            "          create_time DATETIME,\n" +
            "          click_time DATETIME,\n" +
            "          PRIMARY KEY (id)) ")
    int createRecordTable(@Param("tableName") String tableName);

    //根据广告类型查询信息
    @Select("select * from ad_table where ad_type = #{adType}")
    AdTable getIndexByAdType(@Param("adType") String adType);

    //根据包名查询信息
    @Select("select * from ad_table where package_name = #{packageName}")
    List<AdTable> getIndexByPackageName(@Param("packageName") String packageName);

    //搜索所有包名
    @Select("select distinct package_name from ad_table")
    List<String> searchAllPackage();
}
