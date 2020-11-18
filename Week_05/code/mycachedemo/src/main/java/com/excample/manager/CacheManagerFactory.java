package com.excample.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("cacheFactory")
public class CacheManagerFactory {

     ICacheManager iCacheManager;
     @Autowired(required = false)
     ICacheManager redisService;

     public ICacheManager create(String cacheName){
         switch (cacheName){
             case "redis":
                 iCacheManager=redisService;
                 break;
         }
         return iCacheManager;
     }
}
