package com.ldw.loginservice.dao.vo;


import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "User用户对象",description = "新增注册用户")
public class NewUserVo {
    /**
     * 用户名，唯一
     */
    private String username;
    /**
     * 昵称，唯一
     */
    private String nickName;
    /**
     * 电子邮箱，唯一
     */
    private String email;
    /**
     * 手机号码，唯一
     */
    private String phone;
    /**
     * 密码哈希值
     */
    private String passwordHash;
}
