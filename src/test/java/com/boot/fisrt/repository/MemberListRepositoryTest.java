package com.boot.fisrt.repository;

import com.boot.fisrt.domain.Member;
import com.boot.fisrt.domain.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemberListRepositoryTest {

    MemberListRepository dao = new MemberListRepository();

    @AfterEach
    void 리스트초기화(){
        //dao.clearStore();
    }
    @Test
    void 회원저장(){
        // 새로운 맴버를 추가 
        Member member = Member.builder()
                .loginId("test").
                password("1234").
                name("테스트").
                role(Role.STUDENT)
                .build();

        dao.save(member);

        // 추가된 맴버를 찾는 메서드 
        Member m = dao.findOne(member.getId()); // 1L
        System.out.println("member = " + member);
        System.out.println("m = " + m);
        Assertions.assertEquals(member.getName(),"테스트");
    }

    @Test
    void 회원전체출력(){
        List<Member> list = dao.findAll();
        for(Member m : list){
            System.out.println("m = " + m);
        }
        Assertions.assertEquals(4,list.size());
    }

    @Test
    void id로회원찾기(){
        Member m = dao.findByLoginId("test5");
        Assertions.assertEquals(m,null );
    }

    @BeforeEach
    void setData(){
//        Member member = Member.builder(dao.getSequence())
//                .loginId("admin").
//                password("admin").
//                name("관리자").
//                role(Role.ADMIN)
//                .build();
//        Member member1 = Member.builder(dao.getSequence())
//                .loginId("test1").
//                password("1234").
//                name("테스트1").
//                role(Role.STUDENT)
//                .build();
//        Member member2 = Member.builder(dao.getSequence())
//                .loginId("test2").
//                password("1234").
//                name("테스트2").
//                role(Role.STUDENT)
//                .build();
//        Member member3 = Member.builder(dao.getSequence())
//                .loginId("test3").
//                password("1234").
//                name("테스트3").
//                role(Role.STUDENT)
//                .build();
//        dao.save(member);
//        dao.save(member1);
//        dao.save(member2);
//        dao.save(member3);
    }
}