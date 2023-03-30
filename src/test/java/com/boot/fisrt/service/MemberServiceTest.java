package com.boot.fisrt.service;

import com.boot.fisrt.domain.Member;
import com.boot.fisrt.domain.Role;
import com.boot.fisrt.repository.MemberListRepository;
import com.boot.fisrt.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService service = new MemberService();
    MemberListRepository dao = (MemberListRepository)service.getDao();
    @AfterEach
    void 리스트초기화(){
        dao.clearStore();
    }

    @Test
    void 회원가입(){
        Member member = Member.builder(dao.getSequence())
                .loginId("test").
                password("1234").
                name("테스트").
                role(Role.STUDENT)
                .build();
        Long id = service.join(member);
        Assertions.assertEquals(member.getId() , id);
    }
@Test
void 중복회원예외처리(){
    Member member = Member.builder(dao.getSequence())
            .loginId("test2").
            password("1234").
            name("테스트").
            role(Role.STUDENT)
            .build();

    //Assertions.assertThrows(IllegalStateException.class , ()-> service.join(member));
    Assertions.assertThrows(NullPointerException.class , ()-> service.join(member));
}
    @Test
    void 회원탈퇴(){
        service.removeMember(service.getDao().findOne(10L));
        Assertions.assertThrows(IllegalStateException.class , ()-> service.removeMember(service.getDao().findOne(10L)));
    }
    @BeforeEach
    void setData(){
        Member member = Member.builder(dao.getSequence())
                .loginId("admin").
                password("admin").
                name("관리자").
                role(Role.ADMIN)
                .build();
        Member member1 = Member.builder(dao.getSequence())
                .loginId("test1").
                password("1234").
                name("테스트1").
                role(Role.STUDENT)
                .build();
        Member member2 = Member.builder(dao.getSequence())
                .loginId("test2").
                password("1234").
                name("테스트2").
                role(Role.STUDENT)
                .build();
        Member member3 = Member.builder(dao.getSequence())
                .loginId("test3").
                password("1234").
                name("테스트3").
                role(Role.STUDENT)
                .build();
        dao.save(member);
        dao.save(member1);
        dao.save(member2);
        dao.save(member3);
    }


}