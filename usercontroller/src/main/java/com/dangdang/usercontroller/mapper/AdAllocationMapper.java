package com.dangdang.usercontroller.mapper;

import com.dangdang.usercontroller.entity.AdAppTable;
import com.dangdang.usercontroller.entity.Merchant;
import com.dangdang.usercontroller.entity.MerchantConf;
import com.dangdang.usercontroller.entity.Pam.InsAdAppTablePam;
import com.dangdang.usercontroller.entity.Pam.InsMerchantPam;
import com.dangdang.usercontroller.entity.Pam.MerchantAdUpdatePam;
import com.dangdang.usercontroller.entity.Pam.RanDomAdPam;
import com.dangdang.usercontroller.entity.Rtn.RandomAdRtn;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 广告配置mapper
 * @Author songxuan
 * @Create 2020-04-28 10:32
 **/
public interface AdAllocationMapper {


    @Select("select * from ad_table where package_name = #{packageName} and status = 0")
    List<RandomAdRtn> selAppAd(RanDomAdPam ranDomAdPam);

    //查询同一包名下数据条数；
    @Select("select count(*) from ad_table where package_name = #{packageName} and status = 0")
    int countByPackageName(String packageName);


    //获取所有广告app列表
    @Select("SELECT \n" +
            "\tid, app_id as appId, app_name as appName, package_name as packageName, create_teme as createTime, enabled \n" +
            "\tFROM app_table where enabled = 0")
    List<AdAppTable> selectAppTableAll();

    // 添加广告app
    @Insert("insert into app_table(app_id,app_name,package_name,create_time) value(#{appId},#{appName},#{packageName},#{createTime})")
    int insAdAppTable(InsAdAppTablePam adAppTablePam);

    // 根据appId获取app信息
    @Select("SELECT \n" +
            "\tid, app_id as appId, app_name as appName, package_name as packageName, create_teme as createTime, enabled \n" +
            "\tFROM app_table where app_id = #{appId} and enabled = 0")
    AdAppTable selectAppTableById(String appId);

    // 获取商户列表
    @Select("SELECT id, merchant_num as merchantNum, merchant_name as merchantName, create_time as createTime, enabled\n" +
            "FROM merchant where enabled = 0")
    List<Merchant> selectMcAll();


    // 根据商户Id获取商户信息
    @Select("SELECT id, merchant_num as merchantNum, merchant_name as merchantName, create_time as createTime, enabled\n" +
            "FROM merchant where merchant_num = #{mcNum} and  enabled = 0")
    Merchant selectMcById(String mcNum);

    // 新增商户信息
    @Insert("insert into merchant(merchant_num,merchant_name,create_time) value(#{merchantNum},#{merchantName},#{createTIme})")
    int insMerchant(InsMerchantPam insMerchantPam);


    // 获取商户广告配置
    @Select("SELECT id, merchant_id as merchantId, app_id as appId, app_name as appName, status, astrict, award_status as awardStatus, award_money as awardMoney, create_time as createTime, enabled\n" +
            "FROM merchant_conf\n" +
            "where merchant_id = #{merchantId} and enabled = 0")
    List<MerchantConf> merchantConf(String merchantId);


    // 获取商户随机返回广告
    @Select("select \n" +
            "id,app_id as appId,app_name as appName,package_name as packageName \n" +
            "from app_table where app_id \n" +
            "in (select app_id from merchant_conf where merchant_id = #{merchantNum} and status = 0) and enabled = 0")
    List<AdAppTable> selectAppAdPack(String merchantNum);


    // 修改商户广告开启状态
    @Update("update merchant_conf set status = #{status} where id = #{id}")
    int updateMerchantSt(MerchantAdUpdatePam updatePam);


    // 删除商户
    @Update("update merchant set enabled = 1 where merchant_name = #{mcId}")
    int delMerchantById(String mcId);


    // 删除广告
    @Update("update app_table set enabled = 1 where app_id = #{appId}")
    int delAppTabById(String appId);

}
