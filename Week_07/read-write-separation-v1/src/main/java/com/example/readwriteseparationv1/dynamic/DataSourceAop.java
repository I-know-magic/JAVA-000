package com.example.readwriteseparationv1.dynamic;

import cn.hutool.core.util.StrUtil;
import com.example.readwriteseparationv1.dynamic.annotation.DS;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Random;

@Aspect
@Component
public class DataSourceAop {

    @Pointcut("@annotation(datasource)")
    public void piontcut(DS datasource){}

    @Before("piontcut(datasource)")
    public void beforeDs(JoinPoint joinPoint,DS datasource){
        String dataSourceName=datasource.value();
        if(StrUtil.equals(dataSourceName,"read")){
            dataSourceName=RandomRule.rule();
        }
        System.out.println("dataSourceName-->"+dataSourceName);
        MultiDataSource.setDataSourceKey(dataSourceName);
    }

    @After("piontcut(datasource)")
    public void afterDs(JoinPoint joinPoint,DS datasource){
        MultiDataSource.toDefault();
    }

}
