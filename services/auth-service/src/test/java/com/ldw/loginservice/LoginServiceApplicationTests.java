package com.ldw.loginservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class LoginServiceApplicationTests {

    @Test
    void contextLoads() {
        // 获取当前日期时间
        LocalDateTime now = LocalDateTime.now();

        System.out.println(now);
    }

}
