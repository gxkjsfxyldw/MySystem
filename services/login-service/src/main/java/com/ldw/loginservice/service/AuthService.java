package com.ldw.loginservice.service;

import com.ldw.loginservice.common.constant.Result;
import com.ldw.loginservice.dao.vo.NewUserVo;

/**
 * @author ldw by 2025/7/6
 */
public interface AuthService {
    /**
     * 用户注册方法
     * @param newUserVo 用户信息
     * @return 注册结果
     */
    Result register(NewUserVo newUserVo);
}
