package com.excample.controller;

import com.excample.annotation.MyCache;
import com.excample.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class UserController {
    @Resource
    RedisUtil redisUtil;

    @GetMapping("/user")
    @MyCache(value = {"redis"},key = "lvpeng")
    public String testUser(@RequestParam("id") long id){
        log.info("testUser-->"+id);
        return "lvpeng";
    }
}
