package com.excample.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface MyCache {
//    @AliasFor("names")
    String[] value() default {};

    @AliasFor("value")
    String[] names() default {};
    int time() default 60;
    String key() default "";
    String condition() default "";
//    String keyGenerator() default "";
//
//    String cacheManager() default "";
//
//    String cacheResolver() default "";
//
//    String condition() default "";
//
//    String unless() default "";
//
//    boolean sync() default false;
}
