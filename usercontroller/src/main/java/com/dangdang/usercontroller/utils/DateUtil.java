package com.dangdang.usercontroller.utils;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @Author songxuan
 * @Create 2020-04-22 10:25
 **/
public class DateUtil {
    public static String currentTime(){
        SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf1.format(new Date());
    }

    public static Date string2DateTime(String dateTime){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try{
            return sdf.parse(dateTime);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
