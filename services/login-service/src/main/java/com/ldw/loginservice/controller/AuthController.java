package com.ldw.loginservice.controller;

import com.ldw.loginservice.common.constant.Result;
import com.ldw.loginservice.dao.pojo.User;
import com.ldw.loginservice.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @ClassName AuthController
 * @Description TODO
 * @Author liudewang
 * @Date 2022/5/23 14:07
 * @Version 1.0
 **/
@RestController
@Api(tags = "用户登录注册")
@ApiModel("AuthController")
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        // 添加日志记录
        try {
            return authService.register(user);
        } catch (Exception e) {
            // 统一异常处理
            return Result.fail(408,"用户注册信息有误: " + e.getMessage());
        }
    }

}
