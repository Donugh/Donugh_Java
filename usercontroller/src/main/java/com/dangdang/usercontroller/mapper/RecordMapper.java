package com.dangdang.usercontroller.mapper;

import com.dangdang.usercontroller.entity.Record;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RecordMapper {
    @Select("select * from `android_message_pro`")
    List<Record> allRecordList();

    @Select("select * from android_message_pro where id = #{id}")
    Record getRecordById(int id);

    @Delete("delete from android_message_pro where id = #{id}")
    int delRecordById(int id);

    @Delete("delete from ${tableName} where id = #{id}")
    int delRecordByCondition(@Param("tableName") String tableName,@Param("id") int id);

    @Select("<script>" +"select * from android_message_pro where 1 = 1 " +
            "<if test='userId!=null'> and user_id = # {userId}</if>" +
            "<if test='serialno!=null'> and serialno = # {serialno}</if>" +
            "</script>")
    List<Record> searchRecord(String userId,String serialno);

    @Select("<script>"+"select * from android_message_pro where 1 = 1 " +
            "<if test='startTime!=null'> and create_time &gt;= #{startTime,jdbcType=DATE} </if>" +
            "<if test='endTime!=null'> and create_time &lt;= #{endTime,jdbcType=DATE} </if> " +
            "</script>")
    List<Record> searchRecordByTime(@Param("startTime") String startTime , @Param("endTime") String endTime);

    //判断数据库是否存在该表
    @Select("select count(1)\n" +
            "from information_schema.TABLES\n" +
            "where table_name= #{tableName};")
    int isTableExit(@Param("tableName") String name);

    //在有包名、广告商的条件下查询用户信息
    @Select("<script>"+"select * from ${tableName} where 1 = 1 " +
            "<if test='startTime!=null'> and create_time &gt;= #{startTime,jdbcType=DATE} </if>" +
            "<if test='endTime!=null'> and create_time &lt;= #{endTime,jdbcType=DATE} </if> " +
            "</script>")
    List<Record> searchRecordByTimeWithPackageNameAndAdType(@Param("tableName") String tableName,@Param("startTime") String startTime , @Param("endTime") String endTime);
}
