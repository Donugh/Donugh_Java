package com.dangdang.usercontroller.controller;

import com.dangdang.usercontroller.entity.Pam.InsMerchantPam;
import com.dangdang.usercontroller.entity.Pam.MerchantUpdatePam;
import com.dangdang.usercontroller.service.MerchantService;
import com.dangdang.usercontroller.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商户增删改查控制器
 * @Author HuDong
 * @Create 2020-05-09 10:02
 **/
@Slf4j
@RestController
@RequestMapping("merchant/*")
public class MerchantController {
    @Autowired
    private MerchantService merchantService;

    public MerchantController(MerchantService merchantService) {
        this.merchantService = merchantService;
    }


    // 获取所有商户列表
    @GetMapping("/allMerchantList")
    public Result allMerchantList(){
        return merchantService.allMerchantList();
    }

    // 分页获取商户列表
    @PostMapping("/merchantList")
    public Result pageMerchantList(int page){
        return merchantService.pageMerchantList(page);

    }

    //修改商户状态
    @PostMapping("/updateMerchantStatus")
    public Result updateMerchantStatus(MerchantUpdatePam merchantUpdatePam){
        return  merchantService.updateMerchantStatus(merchantUpdatePam.getId(),merchantUpdatePam.getEnabled());
    }


    // 根据id获取商户信息
    @GetMapping("/getMerchantById")
    public Result getMerchantById(Integer id ){
        return merchantService.getMerchantById(id);
    }


    // 添加商户
    @PostMapping("/addMerchant")
    public Result addMerchant(InsMerchantPam insMerchantPam){
        return merchantService.addMerchant(insMerchantPam);
    }

    //修改商户
    @PostMapping("/updateMerchant")
    public Result updateMerchant(MerchantUpdatePam merchantUpdatePam){
        return merchantService.updateMerchant(merchantUpdatePam);
    }

    // 删除商户
    @PostMapping("/delMerchantById")
    public Result delMerchantById(Integer id){
        return merchantService.delMerchantById(id);
    }
}
