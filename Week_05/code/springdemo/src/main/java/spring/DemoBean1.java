package spring;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * Component进入到容器，单例
 * 其他可以通过@Autowired进行装配使用
 */
@Data
@Component("demoBean1")
public class DemoBean1 {
    String name = "11";
    String addr = "11";
    int age = 11;
}
