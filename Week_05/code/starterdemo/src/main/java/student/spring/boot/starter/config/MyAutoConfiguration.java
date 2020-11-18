package student.spring.boot.starter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import student.spring.boot.starter.bean.Student;
import student.spring.boot.starter.properties.MyProperties;
import student.spring.boot.starter.service.MyService;

/**
 *

 @Configuration声明是配置类

 @EnableConfigurationProperties(xxxProperties.class)
 将被 @ConfigurationProperties修饰的类xxxProperties加载到spring容器中

 @ConditionalOnClass(xxxService.class)
 只有在项目路径下加载到xxxService类，才会进行自动配置

 @ConditionalOnProperty(prefix = “xxx”, value = “yyy”)
 检查配置文件中，是否有以xxx开头名称为yy的配置， 由于允许属性缺失，所以仍然会自动配置。

 */
@Configuration
@EnableConfigurationProperties(MyProperties.class)
@ConditionalOnClass(MyService.class)
@ConditionalOnProperty(prefix = "my",value = "enable", matchIfMissing = true)
public class MyAutoConfiguration {
    @Autowired
    private MyProperties myProperties;

    @Bean
    @ConditionalOnMissingBean(MyService.class)
    public MyService myService(){
        MyService myService=new MyService();
        myService.setMyName(myProperties.getMyName());
        myService.setMyAge(myProperties.getMyAge());
        return myService;
    }
    @Bean
    public Student student(){
        Student student=new Student();
        student.setAge(myProperties.getMyAge());
        student.setName(myProperties.getMyName());
        return student;
    }
}
