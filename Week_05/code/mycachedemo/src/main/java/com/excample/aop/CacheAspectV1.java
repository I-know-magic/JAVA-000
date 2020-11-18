package com.excample.aop;

import com.excample.annotation.MyCache;
import com.excample.bean.User;
import com.excample.manager.CacheManagerFactory;
import com.excample.manager.ICacheManager;
import com.excample.utils.MyCacheUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * around > before > around > afterReturning > after
 */
@Component
@Aspect
@Slf4j
public class CacheAspectV1 {
    @Autowired
    CacheManagerFactory cacheFactory;
    @Autowired
    MyCacheUtil cacheUtil;
    @Autowired
    public User cacheUser;
    @Pointcut("@annotation(myCache)")
    public void cacheAspect(MyCache myCache){
        log.info("cacheAspect------->");
    }


    @AfterReturning(value = "cacheAspect(myCache)",returning ="returnObject" )
//    (返回后通知)： 在方法返回后执行
    public void afterReturningCache(JoinPoint joinPoint,MyCache myCache, Object returnObject){
        log.info("afterReturningCache------->"+returnObject.toString());
        Object[] args = joinPoint.getArgs();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String[] argNames =signature.getParameterNames();
        Class[] classes=signature.getParameterTypes();
        String keyName=myCache.key();
//        定义规则例如SPEl ，解析key
        String key=cacheUtil.parsingKey(keyName,argNames,args);
        log.info("key--->"+key);
        String[] value=myCache.value();
        int time=myCache.time();
        for (int i = 0; i <value.length ; i++) {
            ICacheManager iCacheManager=cacheFactory.create("redis");
            iCacheManager.setObj(key,returnObject);
            iCacheManager.expire(key,time);
        }
    }
//    @AfterThrowing
////    afterThrowing 在抛出异常时执行
//    public void afterThrowingCache(){
//
//    }
}
