package bytebuddy.aopdemo;

import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

/**
 * 常用api
 * ByteBuddy
 * 流式API方式的入口类
 * 提供Subclassing/Redefining/Rebasing方式改写字节码
 * 所有的操作依赖DynamicType.Builder进行,创建不可变的对象
 *
 * ElementMatchers(ElementMatcher)
 * 提供一系列的元素匹配的工具类(named/any/nameEndsWith等等)
 * ElementMatcher(提供对类型、方法、字段、注解进行matches的方式,类似于Predicate)
 * Junction对多个ElementMatcher进行了and/or操作
 *
 * DynamicType(动态类型,所有字节码操作的开始,非常值得关注)
 * Unloaded(动态创建的字节码还未加载进入到虚拟机,需要类加载器进行加载)
 * Loaded(已加载到jvm中后,解析出Class表示)
 * Default(DynamicType的默认实现,完成相关实际操作)
 *
 * Implementation(用于提供动态方法的实现)
 * FixedValue(方法调用返回固定值)
 * MethodDelegation(方法调用委托,支持两种方式: Class的static方法调用、object的instance method方法调用)
 *
 * Builder(用于创建DynamicType)
 * MethodDefinition
 * FieldDefinition
 * AbstractBase
 *
 *
 *
 *
 *
 *
 */
@Slf4j
public class UserAopV1 {
    public void testQueryUserById(ClassLoader classLoader) throws Exception{
        DynamicType.Unloaded<?> dynamicType=new ByteBuddy()
                .subclass(UserService.class)
                .method(ElementMatchers.named("queryUserById"))
                .intercept(MethodDelegation.to(UserInterceptV1.class)).make();

        final Class<?> clazz = dynamicType.load(classLoader).getLoaded();
        Object o=clazz.getMethod("queryUserById",long.class).invoke(clazz.newInstance(),1L);
        log.info("UserAopV1---->"+o.toString());
    }
}
