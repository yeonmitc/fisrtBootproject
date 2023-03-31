package com.boot.fisrt.repository;

import com.boot.fisrt.domain.Member;
import com.boot.fisrt.domain.Role;
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
    void 회원가입(){
       setData();
    }

    void setData(){
                Member member = Member.builder()
                .loginId("admin").
                password("admin").
                name("관리자").
                role(Role.ADMIN)
                .build();
        Member member1 = Member.builder()
                .loginId("test1").
                password("1234").
                name("테스트1").
                role(Role.STUDENT)
                .build();
        Member member2 = Member.builder()
                .loginId("test2").
                password("1234").
                name("테스트2").
                role(Role.STUDENT)
                .build();
        Member member3 = Member.builder()
                .loginId("test3").
                password("1234").
                name("테스트3").
                role(Role.STUDENT)
                .build();
        service.join(member);
        service.join(member1);
        service.join(member2);
        service.join(member3);

    }

}