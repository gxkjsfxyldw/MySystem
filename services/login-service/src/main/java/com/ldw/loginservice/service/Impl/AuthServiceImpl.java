package com.ldw.loginservice.service.Impl;

import com.ldw.loginservice.common.constant.Enums;
import com.ldw.loginservice.common.constant.Result;
import com.ldw.loginservice.dao.pojo.User;
import com.ldw.loginservice.service.AuthService;
import com.ldw.loginservice.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @description:
 * @author: ldw
 * @create: 2022-04-07
 **/
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserService userService;



    /**
     * 用户注册
     * @param user
     * @return
     * 设置事务传播行为为 REQUIRED，保证方法在事务内执行
     * propagation: 表示事务传播行为，默认为 Propagation.REQUIRED
     * timeout: 事务超时时间，默认为 -1（不超时）
     */
    @Transactional(propagation = Propagation.REQUIRED, timeout = -1)
    @Override
    public Result register(User user) {
        /**
         * 1.判断参数是否合法
         * 2.判断账户是否已存在，存在，则此账户已被注册
         * 3.如果账户不存在，注册用户
         * 4.注意，加上事务，一旦中间的任何过程问题，注册的用户，需要回滚
         */
        if(StringUtils.isAllBlank(user.getUsername(), user.getPasswordHash(), user.getNickName())) {
            return Result.fail(Result.CODE_BAD_REQUEST, "(账号，密码，昵称)不能为空");
        }
        String result = userService.selecetUserByUsername(user.getUsername());
        // 判断用户名是否已存在,存在则
        if(!"1".equals(result)){
            return Result.fail(Result.CODE_BAD_REQUEST, "(");
        }
        // 获取当前日期时间
        LocalDateTime now = LocalDateTime.now();
        // 格式化为年月日时分秒
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);
        User newUser = User.builder()
                .username(user.getUsername())
                .NickName(user.getNickName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .passwordHash(DigestUtils.md5Hex(user.getPasswordHash()+ Enums.SALT))
                .salt(Enums.SALT)
                .accountStatus("1")
                .failedLoginAttempts(0)
                .lastLoginTime(LocalDateTime.parse(formattedDateTime))
                .lastLoginIp("*")
                .createdAt(LocalDateTime.parse(formattedDateTime))
                .updatedAt(LocalDateTime.parse(formattedDateTime))
                .createdBy((long) 1)
                .updatedBy((long) 1)
                .isDeleted(false)
                .deletionReason(null)
                .deletedAt(null)
                .build();
        if( userService.save(newUser)){
            return Result.success("用户注册成功");
        }
        return Result.fail(408,"用户注册信息有误，注册失败");
    }



}
