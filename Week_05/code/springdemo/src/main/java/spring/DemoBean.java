package spring;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Data
public class DemoBean {
    String name;
    String addr;
    int age;
}
