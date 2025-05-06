package com.ldw.loginservice.service;

import com.common.module.entity.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "login")
public interface test {
    @PostMapping(value = "/login/enter")
    String login(@RequestBody Order order);
}
