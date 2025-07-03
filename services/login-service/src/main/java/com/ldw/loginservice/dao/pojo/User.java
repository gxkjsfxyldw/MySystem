package com.ldw.loginservice.dao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 用户基础信息表
 * @author ldw by 20250701
 */
@Data
@Entity
@Table(name = "users", indexes = {
        @Index(name = "idx_email", columnList = "email"),
        @Index(name = "idx_phone", columnList = "phone"),
        @Index(name = "idx_status", columnList = "account_status")
})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "User")
public class User {

    @Id
    @TableId(value = "id", type = IdType.AUTO)
    @Column(name = "user_id", nullable = false, updatable = false, columnDefinition = "BIGINT COMMENT '用户ID，主键自增'")
    private Long userId;

    @Column(name = "username", nullable = false, unique = true, updatable = true, length = 50, columnDefinition = "VARCHAR(50) COLLATE utf8mb4_unicode_ci COMMENT '用户名，唯一'")
    private String username;

    @Column(name = "email", nullable = false, unique = true, updatable = true, length = 100, columnDefinition = "VARCHAR(100) COLLATE utf8mb4_unicode_ci COMMENT '电子邮箱，唯一'")
    private String email;

    @Column(name = "phone", unique = true, updatable = true, length = 20, columnDefinition = "VARCHAR(20) COLLATE utf8mb4_unicode_ci COMMENT '手机号码，唯一'")
    private String phone;

    @Column(name = "password_hash", nullable = false, updatable = true, length = 255, columnDefinition = "VARCHAR(255) COLLATE utf8mb4_unicode_ci COMMENT '密码哈希值'")
    private String passwordHash;

    @Column(name = "salt", nullable = false, updatable = true, length = 100, columnDefinition = "VARCHAR(100) COLLATE utf8mb4_unicode_ci COMMENT '密码加密盐值'")
    private String salt;

    @Column(name = "account_status", nullable = false, updatable = true, columnDefinition = "ENUM('ACTIVE','INACTIVE','LOCKED','SUSPENDED') COLLATE utf8mb4_unicode_ci DEFAULT 'ACTIVE' COMMENT '账户状态：活跃/未激活/锁定/停用'")
    private String accountStatus;

    @Column(name = "failed_login_attempts", nullable = false, updatable = true, columnDefinition = "INT DEFAULT '0' COMMENT '连续登录失败次数'")
    private Integer failedLoginAttempts;

    @Column(name = "last_login_time", nullable = true, updatable = true, columnDefinition = "DATETIME DEFAULT NULL COMMENT '最后登录时间'")
    private LocalDateTime lastLoginTime;

    @Column(name = "last_login_ip", nullable = true, updatable = true, length = 45, columnDefinition = "VARCHAR(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '最后登录IP地址'")
    private String lastLoginIp;

    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false, updatable = true, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'")
    private LocalDateTime updatedAt;

    @Column(name = "created_by", nullable = true, updatable = false, columnDefinition = "BIGINT DEFAULT NULL COMMENT '创建人ID'")
    private Long createdBy;

    @Column(name = "updated_by", nullable = true, updatable = true, columnDefinition = "BIGINT DEFAULT NULL COMMENT '更新人ID'")
    private Long updatedBy;

    @Column(name = "is_deleted", nullable = false, updatable = true, columnDefinition = "TINYINT(1) DEFAULT '0' COMMENT '是否已删除'")
    private Boolean isDeleted;

    @Column(name = "deletion_reason", nullable = true, updatable = true, length = 255, columnDefinition = "VARCHAR(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '删除原因'")
    private String deletionReason;

    @Column(name = "deleted_at", nullable = true, updatable = true, columnDefinition = "DATETIME DEFAULT NULL COMMENT '删除时间'")
    private LocalDateTime deletedAt;
}
