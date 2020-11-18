package bytebuddy.agentdemo;

import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;

import java.lang.instrument.Instrumentation;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * mvn 配置plugin
 * mvn clean package
 * 配置MANIFEST.MF
 * 配置idea-vm
 *-javaagent:D:\sskj\极客时间\java训练营\作业\第五周作业\code\proxy\target\my-agent.jar=testargs
             D:\sskj\极客时间\java训练营\作业\第五周作业\code\proxy\target\my-agent.jar
 */
@Slf4j
public class MyAgent {
    public static void premain(String agentArgs, Instrumentation inst) {
        log.info("premain--->"+agentArgs);
        final AgentBuilder.Transformer transformer = new AgentBuilder.Transformer() {
            @Override
            public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder, TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule) {
                return builder.method(ElementMatchers.any()).intercept(MethodDelegation.to(MyIntercepter.class));
            }
        };
        final AgentBuilder.Listener listener = new AgentBuilder.Listener() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }

            @Override
            public void onDiscovery(String s, ClassLoader classLoader, JavaModule javaModule, boolean b) {

            }

            @Override
            public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, boolean b, DynamicType dynamicType) {

            }

            @Override
            public void onIgnored(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, boolean b) {

            }

            @Override
            public void onError(String s, ClassLoader classLoader, JavaModule javaModule, boolean b, Throwable throwable) {

            }

            @Override
            public void onComplete(String s, ClassLoader classLoader, JavaModule javaModule, boolean b) {

            }
        };
        new AgentBuilder.Default()
                .type(ElementMatchers.nameStartsWith("bytebuddy.agentdemo"))
                .transform(transformer).with(listener).installOn(inst);
        final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            public void run() {
                JvmInfo.printMemoryInfo();
                JvmInfo.printGC();
                log.info("===================================================================================================");
            }
        }, 0, 5000, TimeUnit.MILLISECONDS);

    }
//    main 运行之后 需要在manifest 设置Agent-Class

    //Can-Retransform-Classes: true
//Can-Redefine-Classes: true
//Attach api。Attach API 在 com.sun.tools.attach
//    VirtualMachine 表示一个Java 虚拟机，也就是程序需要监控的目标虚拟机，提供了获取系统信息(比如获取内存dump、线程dump，
//    类信息统计(比如已加载的类以及实例个数等)， loadAgent，Attach 和 Detach （Attach 动作的相反行为，
//    从 JVM 上面解除一个代理）等方法，可以实现的功能可以说非常之强大 。
//    该类允许我们通过给attach方法传入一个jvm的pid(进程id)，远程连接到jvm上 。
//
//    代理类注入操作只是它众多功能中的一个，通过loadAgent方法向jvm注册一个代理程序agent，
//    在该agent的代理程序中会得到一个Instrumentation实例，该实例可以 在class加载前改变class的字节码，
//    也可以在class加载后重新加载。在调用Instrumentation实例的方法时，
//    这些方法会使用ClassFileTransformer接口中提供的方法进行处理。
//
//    VirtualMachineDescriptor 则是一个描述虚拟机的容器类，配合 VirtualMachine 类完成各种功能。

    public static void agentmain (String agentArgs, Instrumentation inst){

    }
}
