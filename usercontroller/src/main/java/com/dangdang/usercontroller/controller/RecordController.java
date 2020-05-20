package com.dangdang.usercontroller.controller;

import com.dangdang.usercontroller.service.RecordService;
import com.dangdang.usercontroller.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 操作记录控制器
 * @Author HuDong
 * @Create 2020-05-10 10:02
 **/
@Slf4j
@RestController
@RequestMapping("record/*")
public class RecordController {
    @Autowired
    private RecordService recordService;

    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }


    // 获取所有操作记录列表
    @GetMapping("/allRecordList")
    public Result allRecordList(){
        return recordService.allRecordList();
    }

    // 分页获取操作记录列表
    @PostMapping("/pageRecordList")
    public Result pageRecordList(String packageName,String adType ,String startTime ,String endTime,int page){
        if((packageName==null||packageName.equals(""))&&(adType==null||adType.equals(""))){
            return recordService.searchRecord(startTime,endTime,page);
        }else if(adType.equals(null)||adType.equals("")){
            return Result.error("请选择广告商");
        }else{
            return  recordService.searchRecordWithPackageNameAndAdType(packageName,adType ,startTime ,endTime,page);
        }

    }

    // 根据id获取操作记录信息
    @PostMapping("/getRecordById")
    public Result getRecordById(Integer id ){
        return recordService.getRecordById(id);
    }

    // 删除操作记录
    @PostMapping("/delRecordById")
    public Result delRecordById(Integer id){
        return recordService.delRecordById(id);
    }

    //有packageName 、AdType时用户记录删除
    @PostMapping("/delRecordWithFlag")
    public Result delRecordWithFlag(String packageName,String adType,int id){
        return recordService.delRecordWithFlag(packageName,adType,id);
    }

}
