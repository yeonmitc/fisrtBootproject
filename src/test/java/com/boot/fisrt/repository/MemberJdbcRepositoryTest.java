package com.boot.fisrt.repository;

import com.boot.fisrt.domain.Member;
import com.boot.fisrt.domain.Role;
import com.boot.fisrt.service.MemberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//@Transactional  // db 연동테스트 db값 저장은 안함 : rollback
@SpringBootTest
class MemberJdbcRepositoryTest {
    @Autowired
     MemberService service;
    @Autowired
    MemberRepository repository;

    @Test
    void 회원가입(){
       //setData();
        Member member1 = Member.builder()
                .loginId("test7").
                password("7777").
                name("테스트7").
                role(Role.STUDENT)
                .build();

        Long id = service.join(member1);

    }
    
    @Test
    void 전체회원조회(){
        List<Member> list = service.findAllMembers();
        for(Member m : list){
            System.out.println("m = " + m);
        }
    }

    @Test
    void 회원탈퇴(){
        service.removeMember(2L);
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