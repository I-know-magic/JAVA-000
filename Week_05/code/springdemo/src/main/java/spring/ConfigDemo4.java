package spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ConfigDemo4 {
    @Bean(name = "strlist")
    public List<String> stringList() {
        List<String> list = new ArrayList<>();
        list.add("hello");
        list.add("list");
        return list;
    }
}
