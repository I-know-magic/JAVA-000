package cglib;

import bean.Work;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CgLibProxy {
    static void before() {
        System.out.println("====before======");
    }

    static void after() {
        System.out.println("====after======");
    }

    static void before1() {
        System.out.println("====before1======");
    }

    static void after1() {
        System.out.println("====after1======");
    }

    public static void main(String[] args) {

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Work.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                before();
                Object result = methodProxy.invokeSuper(o, objects);
                after();
                return result;
            }
        });
        Work work = (Work) enhancer.create();
        work.working();
    }
}
