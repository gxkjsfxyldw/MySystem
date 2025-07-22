package com.ldw.loginservice.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ldw.loginservice.dao.mapper.UserMapper;
import com.ldw.loginservice.dao.pojo.User;
import com.ldw.loginservice.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: ldw
 * @create: 2022-04-07
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Autowired
    private UserMapper userMapper;


    /**
     * @description: 根据用户名查询用户是否已注册
     * @param: [username]
     * @return: 已注册：1，未注册：0，参数错误，异常：-1
     **/
    @Override
    public String selecetUserByUsername(String username) {
        String result = "0";
        try {
            // 参数校验
            if (StringUtils.isAllBlank(username)) {
                result = "-1";
            }
            //QueryWrapper和LambdaQueryWrapper的区别
            // 查询用户
            User user = baseMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
            // 返回结果
            if (user == null) {
                result = "1";
            }
        } catch (Exception e) {
            // 记录异常日志
            result = "99";
            System.out.println("查询用户信息数据库出错！result = "+result);
        }
        return result;
    }
}
