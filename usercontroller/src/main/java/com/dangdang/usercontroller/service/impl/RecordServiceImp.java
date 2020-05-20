package com.dangdang.usercontroller.service.impl;

import com.dangdang.usercontroller.entity.Record;
import com.dangdang.usercontroller.mapper.RecordMapper;
import com.dangdang.usercontroller.service.RecordService;
import com.dangdang.usercontroller.utils.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class RecordServiceImp implements RecordService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RecordMapper recordMapper;

    @Override
    public Result allRecordList() {
        try {
            List<Record> allRecordList= recordMapper.allRecordList();
            if (allRecordList.size()>0){
                return Result.ok(allRecordList);
            }else{
                return Result.error("无数据");
            }
        }catch (Exception e){
            e.printStackTrace();
            log.error("查询异常");
            return Result.error("查询异常");
        }
    }

    @Override
    public Result pageRecordList(int page) {
        try {
            PageHelper.startPage(1,10);
            PageHelper.clearPage();
            List<Record> allRecordList = recordMapper.allRecordList();

            if(allRecordList.size()>0){
                PageInfo<Record> recordPageInfo = new PageInfo<>(allRecordList);
                recordPageInfo.setTotal(allRecordList.size());
                int startIndex = (page-1)*10;
                if(startIndex<allRecordList.size()){
                    if(startIndex+10<=allRecordList.size()){
                        recordPageInfo.setList(allRecordList.subList(startIndex,startIndex+10));
                    }else{
                        recordPageInfo.setList(allRecordList.subList(startIndex,allRecordList.size()));
                    }
                }else{
                  recordPageInfo.setList(null);
                }
                return Result.ok(recordPageInfo);
            }else{
               return Result.error("无数据");
            }
        }catch (Exception e){
            e.printStackTrace();
            log.error("查询异常");
            return Result.error("查询异常");
        }
    }

    //根据id查询操作记录
    @Override
    public Result getRecordById(Integer id) {
        try{
            Record record = recordMapper.getRecordById(id);
            return Result.ok(record);
        }catch (Exception e){
            e.printStackTrace();
            log.error("查询异常");
            return Result.error("查询异常");
        }
    }

    @Override
    public Result delRecordById(Integer id) {
        try{
            int i = recordMapper.delRecordById(id);
            if(i>0){
                return Result.ok();
            }else{
                return Result.error("删除失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            log.error("查询异常");
            return Result.error("查询异常");
        }
    }

    @Override
    public Result searchRecord(String startTime, String endTime ,int page) {
        try{
//            Date startTime1 = DateUtil.string2DateTime(startTime);
//            Date endTime1 =DateUtil.string2DateTime(endTime);
            PageHelper.startPage(page,10);
            PageHelper.clearPage();
            if(startTime.equals("")){
                startTime=null;
            }
            if(endTime.equals("")){
                endTime=null;
            }
            List<Record> searchedRecord = recordMapper.searchRecordByTime(startTime,endTime );
            if(searchedRecord.size()>0){
                PageInfo<Record> pageInfo = new PageInfo<>(searchedRecord);
                int startIndex = (page-1)*10;
                if(startIndex<searchedRecord.size()){
                    if(startIndex+10<=searchedRecord.size()){
                        pageInfo.setList(searchedRecord.subList(startIndex,startIndex+10));
                    }else{
                        pageInfo.setList(searchedRecord.subList(startIndex,searchedRecord.size()));
                    }
                }else{
                    pageInfo.setList(null);
                }
                return Result.ok(pageInfo);
            }else{
                return Result.error("无数据");
            }
        }catch (Exception e){
            e.printStackTrace();
            log.error("搜索异常");
            return Result.error("搜索异常");
        }
    }

    @Override
    public Result searchRecordWithPackageNameAndAdType(String packageName, String adType, String startTime, String endTime, int page) {
        try{
            String tableName = packageName.trim().replace(".","_") + "_"+adType.trim();
            int i = recordMapper.isTableExit(tableName);
            if(i>0){
                if(startTime.equals("")){
                    startTime=null;
                }
                if(endTime.equals("")){
                    endTime=null;
                }
               List<Record> recordList = recordMapper.searchRecordByTimeWithPackageNameAndAdType(tableName,startTime,endTime);
               if(recordList.size()>0){
                   PageInfo<Record> pageInfo = new PageInfo<>(recordList);
                   int startIndex = (page-1)*10;
                   if(startIndex<recordList.size()){
                       if(startIndex+10<=recordList.size()){
                           pageInfo.setList(recordList.subList(startIndex,startIndex+10));
                           for(Record record : pageInfo.getList()){
                               record.setIsDefault(1);
                           }
                       }else{
                           pageInfo.setList(recordList.subList(startIndex,recordList.size()));
                           for(Record record : pageInfo.getList()){
                               record.setIsDefault(1);
                           }
                       }
                   }else{
                       pageInfo.setList(null);
                   }
                   return Result.ok(pageInfo);
               } else{
                   return Result.error("无与此广告商对应的用户数据");
               }
            }else{
                return Result.error("无与此广告商对应的用户数据表");
            }
        }catch (Exception e){
            e.printStackTrace();
            log.error("根据包名、广告商检索用户信息异常");
            return Result.error("根据包名、广告商检索用户信息异常");
        }

    }

    @Override
    public Result delRecordWithFlag(String packageName, String adType, int id) {
        try {
            String tableName =packageName.replace(".","_") + "_" +adType;
            int i = recordMapper.delRecordByCondition(tableName,id);
            if(i>0){
                return Result.ok();
            }else{
                return Result.error("删除失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            log.error("删除异常");
            return Result.error("删除异常");
        }
    }
}
