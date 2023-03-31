package com.boot.fisrt.repository;

import com.boot.fisrt.domain.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberJdbcRepository implements MemberRepository{

    private final JdbcTemplate jdbcTemplate;

    public MemberJdbcRepository(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Member save(Member member) {

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        // db 가 자동으로 id값을 1증가해서 가져오기 때문에
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("login_id" , member.getLoginId());
        parameters.put("name", member.getName());
        parameters.put("password",member.getPassword());
        parameters.put("role",member.getRole().toString());

        // 자동 증가한 값이 뭔지를 다시 꺼내와야함
        Number key = jdbcInsert.executeAndReturnKey(
                new MapSqlParameterSource(parameters));

        member.setKeyId(key.longValue());

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
