package com.ldw.loginservice.service;

import com.ldw.loginservice.dao.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

public interface UserService extends IService<User>{
    /**
     * 根据用户名查询
     */
    String selecetUserByUsername(String username);
}
