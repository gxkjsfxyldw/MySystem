package com.ldw.loginservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootTest
class LoginServiceApplicationTests {

    @Test
    void contextLoads() {
        // 获取当前日期时间
        LocalDateTime now = LocalDateTime.now();
        // 格式化为年月日时分秒
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);
        System.out.println(formattedDateTime);
    }

}
