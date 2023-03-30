package com.boot.fisrt.repository;

import com.boot.fisrt.domain.Member;

import java.util.List;

public interface MemberRepository {

    Member save(Member member);
    List<Member> findAll();
    Member findOne(Long id);
    Member findByLoginId(String loginId);
    void delete(Member member);
}
