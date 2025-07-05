package com.ldw.loginservice.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *  MyBatis Plus 全局配置类，配置分页插件
 * 支持 MySQL、PostgreSQL、Oracle 等数据库的物理分页。
 */
@Configuration
public class MybatisPlusConfig {
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
