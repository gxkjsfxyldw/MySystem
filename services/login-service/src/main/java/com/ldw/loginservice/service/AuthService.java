package com.ldw.loginservice.service;

import com.common.module.constant.Result;
import com.ldw.loginservice.dao.pojo.User;

/**
 * @author ldw by 2025/7/6
 */
public interface AuthService {
    /**
     * 用户注册方法
     * @param user 用户信息
     * @return 注册结果
     */
    Result register(User user);
}
