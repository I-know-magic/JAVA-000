package com.example.readwriteseparationv1;

import com.example.readwriteseparationv1.config.DataSourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@Import({DataSourceConfig.class})
public class ReadWriteSeparationV1Application {

    public static void main(String[] args) {
        SpringApplication.run(ReadWriteSeparationV1Application.class, args);
    }

}
