package com.dangdang.usercontroller.controller;

import com.dangdang.usercontroller.service.UserService;
import com.dangdang.usercontroller.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/userLogin")
    public Result userLogin(String userName,String Password){
        return userService.userLoginService(userName, Password);
    }

}
