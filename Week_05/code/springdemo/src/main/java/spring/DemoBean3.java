package spring;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 通过Configuration和Bean加入到容器
 */
@Data
public class DemoBean3 {
    @Value("name-3333")
    String name = "33";
    @Value("addr-3333")
    String addr = "33";
    int age = 3;
}
