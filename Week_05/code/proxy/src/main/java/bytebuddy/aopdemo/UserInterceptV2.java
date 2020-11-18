package bytebuddy.aopdemo;

import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;

/***
 * @Advice.OnMethodEnter
 *被标注此注解的方法会被织入到被bytebudy增强的方法前调用
 *
 *     Advice.Argument
 *     用来获取 被执行对象的field或者参数
 *     Advice.This
 *     是当前advice对象的载体，标注之后，方法内部可以获取目前advice对象
 *     @ Advice.OnMethodExit
 *
 *     Advice.Argument
 *     用来获取 被执行对象的field或者参数
 *     Advice.This
 *     是当前advice对象的载体，标注之后，方法内部可以获取目前advice对象。
 *     Advice.Return
 *     给一个参数标识Advice.Return 后可以接收返回结果。
 *     Advice.Thrown
 *     给一个参数标识Advice.Thrown 后可以接收抛出的异常。如果方法引发异常，则该方法将提前终止。
 *     如果由Advice.OnMethodEnter注释的方法引发异常，则不会调用由Advice.OnMethodExit方法注释的方法。如果检测的方法引发异常，则仅当Advice.OnMethodExit.onThrowable()属性设置为true（默认值）时，才调用由Advice.OnMethodExit注释的方法。

 *
 *

 */
//使用slf4j时报错,是否是冲突，不清楚，没有找到原因。
//@Slf4j
public class UserInterceptV2 {
    @Advice.OnMethodEnter
    public static Object onMethodEnter(@Advice.Origin Method method, @Advice.AllArguments Object[] arguments){
//        log.info("befor=======>");
        System.out.println("before=======>");
        return null;
    }
    @Advice.OnMethodExit
    public static Object onMethodExit(@Advice.Origin Method method, @Advice.AllArguments Object[] arguments){
        System.out.println("end=======>");
        return null;
    }


}
