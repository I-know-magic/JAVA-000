package spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigDemo {
    @Bean
    public DemoBean3 demoBean3() {
        return new DemoBean3();
    }
}
