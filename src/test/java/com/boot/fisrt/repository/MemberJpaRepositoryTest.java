package com.boot.fisrt.repository;

import com.boot.fisrt.domain.Member;
import com.boot.fisrt.domain.Role;
import com.boot.fisrt.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberJpaRepositoryTest {


    @Autowired
    MemberRepository repository;

    @Test
    void 회원가입(){
        Member member1 = Member.builder()
                .loginId("test7").
                password("7777").
                name("테스트7").
                role(Role.STUDENT)
                .build();
        repository.save(member1);
    }


}