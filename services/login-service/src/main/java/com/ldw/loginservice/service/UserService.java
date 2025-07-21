package com.ldw.loginservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ldw.loginservice.dao.pojo.User;

public interface UserService extends IService<User>{
    /**
     * 根据用户名查询
     */
    String selecetUserByUsername(String username);
    /**
     * 根据用户id查询
     * @param userIds
     */
    User getUserListByUserIds(Long userIds);
}
