package com.ldw.loginservice.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.common.module.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
//import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/user")
@RefreshScope
public class LoginContrller {

//        //application.yml配置文件中，设置token在redis中的过期时间
//        @Value("${config.redisTimeout}")
//        private Long redisTimeout;

//        @Autowired
//        private RedisTemplate redisTemplate;

        @GetMapping(value = "/login")
        public String login (){

            //验证账号密码
            String userId = "123";
            //jwt生成token
            String token = JwtUtil.getToken(userId);
            //将token存入redis
//            redisTemplate.opsForValue().set(token,userId,redisTimeout, TimeUnit.SECONDS);
            //将token返回客户端
            return token;
        }

        @GetMapping(value = "/test/timeout")
        public String timeout (){

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "111";
        }

        @GetMapping(value = "/test/exception")
        public String exception (){

            int i = 10/0;
            return "111";
        }

        @GetMapping(value = "/test/hotKey")
        @SentinelResource(value = "testHotKey",blockHandler = "testHotKeyBlockHandler")
        public String testHotKey (@RequestParam(value = "p1",required = false) String p1, @RequestParam(value = "p2",required = false) String p2){

            return "testHotKey";
        }

        public String testHotKeyBlockHandler (String p1, String p2, BlockException e){

            return "testHotKey,热点流控降级";
        }





}
