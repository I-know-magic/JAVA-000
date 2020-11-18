package test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RestController;
import student.spring.boot.starter.bean.Student;
import student.spring.boot.starter.service.MyService;

import javax.annotation.Resource;

@SpringBootApplication
@RestController
public class testContoller {
//    @Autowired(required = false)
    @Resource
    public MyService myService;
    @Autowired(required = false)
    public Student student;
    @GetMapping("/myservice")
    public String queryService(){
        return myService.queryName();
    }
    @GetMapping("/student")
    public String queryStudent(){
        return student.getName();
    }
    public static void main(String[] args) {
        SpringApplication.run(testContoller.class,args);
    }
}
