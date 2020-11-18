package bytebuddy.aopdemo;

import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bind.annotation.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;

/***
 *  @RuntimeType：定义运行时的目标方法。@SuperCall：用于调用父类版本的方法。
 *     callable.call(); 调用原方法的内容，返回结果。而前后包装的。
 *@Origin，用于拦截原有方法，这样就可以获取到方法中的相关信息
 * @AllArguments 、@Argument(0)，一个用于获取全部参数，一个获取指定的参数
 * 注解	说明
 * @Argument 绑定单个参数
 * @AllArguments 绑定所有参数的数组
 * @This 当前被拦截的、动态生成的那个对象
 * @Super 当前被拦截的、动态生成的那个对象的父类对象
 * @Origin 可以绑定到以下类型的参数：Method 被调用的原始方法 Constructor 被调用的原始构造器 Class 当前动态创建的类 MethodHandle MethodType String 动态类的toString()的返回值 int 动态方法的修饰符
 * @DefaultCall 调用默认方法而非super的方法
 * @SuperCall 用于调用父类版本的方法
 * @Super 注入父类型对象，可以是接口，从而调用它的任何方法
 * @RuntimeType 可以用在返回值、参数上，提示ByteBuddy禁用严格的类型检查
 * @Empty 注入参数的类型的默认值
 * @StubValue 注入一个存根值。对于返回引用、void的方法，注入null；对于返回原始类型的方法，注入0
 * @FieldValue 注入被拦截对象的一个字段的值
 * @Morph 类似于@SuperCall，但是允许指定调用参数
 *
 *

 */
@Slf4j
public class UserInterceptV1 {
    @RuntimeType
    public static Object intercept(@SuperCall Callable<?> callable, @Origin Method method) throws Exception {
        long start = System.currentTimeMillis();
        log.info("intercept--->");
        try {
            log.info(callable.toString());
//            log.info(sclazz.toString());
            Class<?> clazz=callable.getClass();
            log.info(clazz.toString());

            final Object call = callable.call();
            log.info("call before--->"+call.toString());

//            改变结果
            Field field=call.getClass().getDeclaredField("name");
            field.set(call,"sjsjsj");
            log.info("call end--->"+call.toString());
            return call;
        } finally {
            log.info("method-name：" + method.getName());
            log.info("params-count：" + method.getParameterCount());
            log.info("params-type：" + method.getParameterTypes()[0].getTypeName());
            log.info("return-type：" + method.getReturnType().getName());
            log.info("times：" + (System.currentTimeMillis() - start) + "ms");
        }
    }

}
