package com.ldw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.web.util.pattern.PatternParseException;

/**
 * @author ldw by 20250701
 */
@SpringBootApplication
//@EnableConfigurationProperties(value = PatternParameter.class)
public class LoginServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoginServiceApplication.class, args);
    }

}
