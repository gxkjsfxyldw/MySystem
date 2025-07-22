package com.ldw.loginservice.dao.vo;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "User用户对象",description = "登录之后的返回用户信息")
public class UserVo {

    /**
     * 用户ID，主键自增
     */
    private Long userId;

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
     * 账户状态：活跃/未激活/锁定/停用
     * 可选值: "ACTIVE", "INACTIVE", "LOCKED", "SUSPENDED"
     */
    private String accountStatus;

    /**
     * 连续登录失败次数
     */
    private Integer failedLoginAttempts;

    /**
     * 最后登录时间
     */
    private LocalDateTime lastLoginTime;

    /**
     * 最后登录IP地址
     */
    private String lastLoginIp;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;

    /**
     * 创建人ID
     */
    private Long createdBy;

    /**
     * 更新人ID
     */
    private Long updatedBy;
}
