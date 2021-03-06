package com.excample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;

@SpringBootApplication
@Cacheable
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
