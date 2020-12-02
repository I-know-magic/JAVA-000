package com.example.readwriteseparationv1;

//import com.example.readwriteseparationv1.domain.primary.User;
//import com.example.readwriteseparationv1.repository.IRepository;
//import com.example.readwriteseparationv1.repository.primary.PrimaryUserRepository;
//import com.example.readwriteseparationv1.repository.second.SecondUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.annotation.Resource;

@SpringBootTest
class ReadWriteSeparationV1ApplicationTests {

//    @Autowired
//    @Qualifier("primaryUserRepository")
//    JpaRepository primaryUserRepository;
//
//    @Autowired
//    @Qualifier("secondUserRepository")
//    JpaRepository secondUserRepository;
    @Test
    void contextLoads() {
    }
//    @Test
//    void primaryDataSource() {
//        System.out.println(((User)primaryUserRepository.findAll().get(0)).getName());
//    }
//
//    @Test
//    void secondSource() {
//        System.out.println(((com.example.readwriteseparationv1.domain.second.User)secondUserRepository.findAll().get(0)).getName());
//
//    }
}
