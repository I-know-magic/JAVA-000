package com.excample.test;

import com.excample.annotation.MyCache;
import com.excample.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class UserTest {
    @Resource
    RedisUtil redisUtil;

    @GetMapping("/user/query")
    public String getUserById(@RequestParam("id") String id){
        log.info("getUserById-->"+id);
        Object result=redisUtil.get(id);
        return StringUtils.isEmpty(result)?"no cache":result.toString();
    }
}
