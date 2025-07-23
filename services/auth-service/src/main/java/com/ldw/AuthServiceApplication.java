package com.ldw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.web.util.pattern.PatternParseException;

/**
 * @author ldw by 20250701
 */
@SpringBootApplication
@EnableDiscoveryClient
//@EnableConfigurationProperties(value = PatternParameter.class)
public class AuthServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class, args);
    }

}
