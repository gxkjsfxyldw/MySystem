package com.ldw.loginservice.dao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 用户基础信息表
 * @author ldw by 20250701
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "User")
public class User {

    /**
     * 用户ID，主键自增
     */
    @TableId(value = "user_id", type = IdType.AUTO)
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
     * 密码哈希值
     */
    private String passwordHash;

    /**
     * 密码加密盐值
     */
    private String salt;

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

    /**
     * 是否已删除
     */
    private Boolean isDeleted;

    /**
     * 删除原因
     */
    private String deletionReason;

    /**
     * 删除时间
     */
    private LocalDateTime deletedAt;
}
