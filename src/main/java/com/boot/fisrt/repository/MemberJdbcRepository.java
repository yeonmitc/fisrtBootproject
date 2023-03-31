package com.boot.fisrt.repository;

import com.boot.fisrt.domain.Member;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class MemberJdbcRepository implements MemberRepository{

    private final JdbcTemplate jdbcTemplate;

    public MemberJdbcRepository(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Member save(Member member) {
        return null;
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
