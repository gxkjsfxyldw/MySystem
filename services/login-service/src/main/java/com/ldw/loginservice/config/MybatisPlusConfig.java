package com.ldw.loginservice.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *  MyBatis Plus 全局配置类，配置分页插件
 * 支持 MySQL、PostgreSQL、Oracle 等数据库的物理分页。
 * @author 26327
 */
@Configuration
public class MybatisPlusConfig {
    /**
     * 新的分页插件（推荐使用）
     * 注意：如果使用 MySQL、Oracle、PostgreSQL 等数据库，需要设置 DbType
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 添加分页插件，并指定数据库类型（这里以 MySQL 为例）
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
}
