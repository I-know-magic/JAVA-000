package bytebuddy.classdemo;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.implementation.MethodDelegation;

import java.io.File;
import java.lang.reflect.Modifier;
import java.net.URLDecoder;

import static net.bytebuddy.matcher.ElementMatchers.named;

/**
 *
 */
public class HelloByteBuddy {
    public String hello() throws IllegalAccessException, InstantiationException {
        String helloWorld = new ByteBuddy()
                .subclass(Object.class)
                .method(named("toString"))
                .intercept(FixedValue.value("Hello World!"))
                .make()
                .load(getClass().getClassLoader())
                .getLoaded()
                .newInstance()
                .toString();
        return helloWorld;
    }

    public void byteBuddyDemo() throws Exception{
        DynamicType.Unloaded<?> dynamicType = new ByteBuddy()
                .subclass(Object.class)
                .name("xxxx.HelloWorld1")
//        定义方法；名称、返回类型、属性public static
                .defineMethod("main", void.class, Modifier.PUBLIC + Modifier.STATIC)
//                定义参数
                .withParameter(String[].class, "args")
//                返回值
//                .intercept(FixedValue.value("Hello World!"))
//                委托
//                MethodDelegation，需要是 public 类
//    被委托的方法与需要与原方法有着一样的入参、出参、方法名，否则不能映射上
                .intercept(MethodDelegation.to(Hello.class))
                .make();
        String pathName = HelloByteBuddy.class.getResource("/").getPath() + "HelloWorld";
        String pathName1= URLDecoder.decode(pathName,"UTF-8");
        dynamicType.saveIn(new File( pathName1));
        System.out.println("类输出路径：" + pathName1);

        // 加载类
        Class<?> clazz = dynamicType.load(HelloByteBuddy.class.getClassLoader())
                .getLoaded();

        // 反射调用
        clazz.getMethod("main", String[].class).invoke(clazz.newInstance(), (Object) new String[1]);

    }

}
