package com.excample.manager.redis;

import com.excample.manager.ICacheManager;
import com.excample.utils.RedisUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RedisService implements ICacheManager {
    @Resource
    RedisUtil redisUtil;

    public void expire(String key, long time){
        redisUtil.expire(key,time);
    }

    public void setObj(String key , Object o){
        redisUtil.set(key,o);
    }
    public Object  getObj(String key){
        return redisUtil.get(key);
    }
}
