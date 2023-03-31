package com.boot.fisrt.repository;

import com.boot.fisrt.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberJdbcRepositoryTest {
    @Autowired
     MemberService service;
    @Autowired
    MemberRepository repository;
    
    @Test
    void db연결(){
        System.out.println("service = " + service);
        System.out.println("repository = " + repository);
    }
}