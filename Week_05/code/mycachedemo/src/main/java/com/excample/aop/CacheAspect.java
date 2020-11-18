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
 * 可以直接使用v1，基于注解的
 */
@Component
@Aspect
@Slf4j
public class CacheAspect {
    @Autowired
    CacheManagerFactory cacheFactory;
    @Autowired
    MyCacheUtil cacheUtil;
    @Autowired
    public User cacheUser;
    /**
     * 1）execution(* *(..))
     * //表示匹配所有方法
     * 2）execution(public * com. savage.service.UserService.*(..))
     * //表示匹配com.savage.server.UserService中所有的公有方法
     * 3）execution(* com.savage.server..*.*(..))
     * //表示匹配com.savage.server包及其子包下的所有方法
     */
//    @Pointcut("execution(* com.excample.controller..*.*(..))")
//    public void cacheAspect(){
//        log.info("cacheAspect------->");
//    }
//
//    @Before("cacheAspect()")
////    在方法开始执行前执行
//    public void beforeCache(){
//        log.info("beforeCache------->");
//
//    }
//
//    @After(value = "cacheAspect()")
////    在方法执行后执行
//    public void afterCache(JoinPoint joinPoint){
//        log.info("afterCache------->");
//    }
//
//    @Around("cacheAspect()")
//    public Object aroundCache(ProceedingJoinPoint joinPoint){
//        log.info("aroundCache------->");
//        try {
//            return joinPoint.proceed();
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
//        return null;
//    }
//    @AfterReturning(value = "cacheAspect()",returning ="returnObject" )
////    (返回后通知)： 在方法返回后执行
//    public void afterReturningCache(JoinPoint joinPoint, Object returnObject){
////        log.info("afterReturningCache------->"+returnObject.toString());
////        Object[] args = joinPoint.getArgs();
////        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
////        Method method = signature.getMethod();
////        String[] argNames =signature.getParameterNames();
////        Class[] classes=signature.getParameterTypes();
////        MyCache myCache=method.getAnnotation(MyCache.class);
////        String keyName=myCache.key();
//////        定义规则例如SPEl ，解析key
////        String key=cacheUtil.parsingKey(keyName,argNames,args);
////        log.info("key--->"+key);
////        String[] value=myCache.value();
////        int time=myCache.time();
////        for (int i = 0; i <value.length ; i++) {
////            ICacheManager iCacheManager=cacheFactory.create("redis");
////            iCacheManager.setObj(key,returnObject);
////            iCacheManager.expire(key,time);
////        }
//    }
//    @AfterThrowing
////    afterThrowing 在抛出异常时执行
//    public void afterThrowingCache(){
//
//    }
}
