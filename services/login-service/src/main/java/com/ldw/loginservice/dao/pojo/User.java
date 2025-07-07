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

    @TableId(value = "id", type = IdType.AUTO)
    private Long userId;

    private String username;

    private String email;

    private String phone;

    private String passwordHash;

    private String salt;

    private String accountStatus;

    private Integer failedLoginAttempts;

    private LocalDateTime lastLoginTime;

    private String lastLoginIp;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Long createdBy;

    private Long updatedBy;

    private Boolean isDeleted;

    private String deletionReason;

    private LocalDateTime deletedAt;
}
