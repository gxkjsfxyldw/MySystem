package com.ldw.loginservice.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service")
public interface LoginServiceFeignClient {
    @PostMapping("login")
    String login(String username, String password);

}
