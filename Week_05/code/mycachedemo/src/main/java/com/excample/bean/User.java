package com.excample.bean;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component("cacheUser")
@Data
public class User {
    public String name;
    public long id;
}
