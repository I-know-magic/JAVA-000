package jdk;

import bean.Person;
import bean.Student;
import bean.Student1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxyMain {
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
        final Student student = new Student();
        final Student1 student1 = new Student1();
        Person person = (Person) Proxy.newProxyInstance(Student.class.getClassLoader(), new Class[]{Person.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                before();
                Object o = method.invoke(student, args);
                after();
                return o;
            }
        });
        Person person1 = (Person) Proxy.newProxyInstance(Student1.class.getClassLoader(), new Class[]{Person.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                before1();
                Object o = method.invoke(student1, args);
                after1();
                return o;
            }
        });
        System.out.println("reading-----------");
        person.reading();
        System.out.println("eating-----------");
        person.eating();
        System.out.println("running-----------");
        person.running();

        System.out.println("person1-reading-----------");
        person1.reading();
        System.out.println("person1-eating-----------");
        person1.eating();
        System.out.println("person1-running-----------");
        person1.running();
    }
}
