package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
//    @Autowired
//    private DemoBean1 demoBean1;

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ApplicationContext context1 = new AnnotationConfigApplicationContext(ConfigDemo.class);
        System.out.println(context.getBean("demoBean2").getClass().getName());
        System.out.println(context.getBean("demoBean1").getClass().getName());
        System.out.println(context.getBean("demoBean3").getClass().getName());
        System.out.println(context.getBean("demoBean2"));
        System.out.println(context.getBean("demoBean1"));
        System.out.println(context.getBean("demoBean3"));
        System.out.println(context.getBean("demoBean4"));
        System.out.println(context.getBean("strlist"));
        System.out.println("Annotation->" + context1.getBean("demoBean3"));

    }
}
