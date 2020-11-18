package com.excample.utils;

import cn.hutool.core.util.StrUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Author lvpeng
 * @Date 2020-11-18
 * @Version 1.0
 **/
@Component("cacheUtil")
public final class MyCacheUtil {

    public  String parsingKey(String expr,String[] argNames,Object[] args, Object data) {
        String tem="";
        ExpressionParser expressionParser = new SpelExpressionParser();
        Expression expression = expressionParser.parseExpression(expr);
        EvaluationContext context = new StandardEvaluationContext();
        Field[] fields=data.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field=fields[i];
            String name=field.getName();
            for (int j = 0; j <argNames.length ; j++) {
                String argName=argNames[j];
                if(j == 0){
                    tem=argName+"-"+args[j];
                }
                if(argName.equals(name)){
                    try {
                        field.set(data,args[j]);

                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        System.out.println(data);
        String name= StrUtil.lowerFirst(data.getClass().getSimpleName());
        context.setVariable(name, data);
        Object o=expression.getValue(context);
        String key="";
        if(o==null){
            key=tem;
        }else {
            key=o.toString();
        }
        return key;
    }
    public  String parsingKey(String expr,String[] argNames,Object[] args) {
        ExpressionParser expressionParser = new SpelExpressionParser();
        Expression expression = expressionParser.parseExpression(expr);
        EvaluationContext context = new StandardEvaluationContext();
        for (int i = 0; i <argNames.length ; i++) {
            context.setVariable(argNames[i], argNames[i]+"-"+args[i]);
        }

        return expression.getValue(context).toString();
    }
    public String parsingKey(String expr, Map<String, Object> map) {

        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression(expr, new TemplateParserContext());

        EvaluationContext context = new StandardEvaluationContext();

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            context.setVariable(entry.getKey(), entry.getValue());
        }

        return expression.getValue(context, String.class);
    }
}
