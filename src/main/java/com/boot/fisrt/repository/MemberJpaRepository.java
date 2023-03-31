package com.boot.fisrt.repository;

import com.boot.fisrt.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;

public class MemberJpaRepository implements MemberRepository{
    private final EntityManager em;
    public MemberJpaRepository(EntityManager em){
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public List<Member> findAll() {

        return null;
    }

    @Override
    public Member findOne(Long id) {
        return null;
    }

    @Override
    public Member findByLoginId(String loginId) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
