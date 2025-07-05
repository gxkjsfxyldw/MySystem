package com.ldw.loginservice.service.impl;

import com.common.module.constant.Result;
import com.ldw.loginservice.dao.mapper.UserService;
import com.ldw.loginservice.dao.pojo.User;
import com.ldw.loginservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: ldw
 * @create: 2022-04-07
 **/
@Service
public class AuthServiceIpml implements AuthService {

    @Autowired
    private UserService userService;

    /**
     * 1.判断参数是否合法
     * 2.判断账户是否已存在，存在，则此账户已被注册
     * 3.如果账户不存在，注册用户
     * 4.注意，加上事务，一旦中间的任何过程问题，注册的用户，需要回滚
     */
    @Override
    public Result register(User user) {

        return Result.success();
    }
}
