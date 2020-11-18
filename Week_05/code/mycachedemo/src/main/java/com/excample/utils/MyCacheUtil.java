package com.excample.utils;

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

//    public  String parsingKey(String expr, Object data) {
//        ExpressionParser parser = new SpelExpressionParser();
//        Expression expression = parser.parseExpression(expr, new TemplateParserContext());
//        return expression.getValue(data, String.class);
//    }
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
