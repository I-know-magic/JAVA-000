package spring;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 自动装配
 */
@Data
@Component("demoBean4")
public class DemoBean4 {
    private DemoBean3 demoBean3;
    private DemoBean1 demoBean1;
    @Autowired
    private DemoBean demoBean;

    @Autowired
    public DemoBean4(DemoBean3 demoBean3) {
        this.demoBean3 = demoBean3;
    }

    @Autowired
    public void setDemoBean1(DemoBean1 demoBean1) {
        this.demoBean1 = demoBean1;
    }

    @Autowired
    private List<String> list;


    public List<String> getList() {
        return list;
    }

    @Value("name-4444")
    String name;
    @Value("addr-4444")
    String addr;
    int age = 3;
}
