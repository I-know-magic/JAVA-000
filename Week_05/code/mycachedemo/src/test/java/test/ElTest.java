package test;

import org.junit.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class ElTest {

    @Test
    public void testEl(){
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression("'#{Hello World}'");
        String message = exp.getValue(String.class);
        System.out.println(message);
    }
}
