package com.harlaus.controller;


import com.harlaus.entity.CommonResult;
import com.harlaus.entity.User;
import com.harlaus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/punch")
public class RegisterController {

    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public CommonResult register(@RequestBody List<User> users){
        return userService.doRegister(users);
    }
}
