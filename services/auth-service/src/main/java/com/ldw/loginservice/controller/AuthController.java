package com.ldw.loginservice.controller;

import com.ldw.loginservice.common.constant.Result;
import com.ldw.loginservice.dao.vo.NewUserVo;
import com.ldw.loginservice.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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

    @GetMapping("/login")
    public Result login(@RequestParam String username, @RequestParam String password){
        try {
            return authService.login(username,password);
        } catch (Exception e) {
            // 统一异常处理
            return Result.fail(408,"账号或密码信息有误: " + e.getMessage());
        }
    }

    @PostMapping("/register")
    public Result register(@RequestBody NewUserVo newUserVo) {
        // 添加日志记录
        try {
            return authService.register(newUserVo);
        } catch (Exception e) {
            // 统一异常处理
            return Result.fail(408,"用户注册信息有误: " + e.getMessage());
        }
    }

}
