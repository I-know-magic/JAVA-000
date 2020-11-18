package bytebuddy.aopdemo;

import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.ByteBuddy;

import net.bytebuddy.asm.Advice;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

/**
错误！
 */
@Slf4j
public class UserAopV2 {
    public static void testQueryUserById(ClassLoader classLoader) throws Exception{
        DynamicType.Unloaded<?> dynamicType=new ByteBuddy()
                .subclass(UserServiceV2.class)
                .method(ElementMatchers.any())
                .intercept(Advice.to(UserInterceptV2.class)).make();
        final Class<?> clazz = dynamicType.load(classLoader).getLoaded();
        Object o=clazz.getMethod("printA",long.class).invoke(clazz.newInstance(),1L);
        log.info("UserAopV2---->"+o.toString());
        UserServiceV2 userServiceV2= (UserServiceV2) dynamicType.load(UserServiceV2.class.getClassLoader()).getLoaded().newInstance();

        userServiceV2.printA(1);

    }

    public static void main(String[] args) throws Exception{
        testQueryUserById(null);
    }
}
