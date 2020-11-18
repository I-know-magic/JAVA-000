package test;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;

@Slf4j
public class ElTest {

    @Test
    public void testEl(){
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression("'#{Hello World}'");
        String message = exp.getValue(String.class);
        System.out.println(message);
    }


    @Test
    public void testParse() {
        //表达式解析
        ExpressionParser expressionParser = new SpelExpressionParser();
        Expression expression = expressionParser.parseExpression("#itemId");
        EvaluationContext context = new StandardEvaluationContext();
        context.setVariable("itemId", "itemId-100222");
        context.setVariable("name", "1020");
        log.info("[SpELTest - testParse ] {} ", expression.getValue(context));
        //获取方法参数名
//        LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();
//        for (Method method : new StringUtils().getClass().getDeclaredMethods()) {
//            for (String s : discoverer.getParameterNames(method)) {
//                System.out.print("parm: "+s+"  ");
//            }
//            System.out.println("methodName:  "+method.getName());
//        }
    }
}
