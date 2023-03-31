package com.boot.fisrt.repository;

import com.boot.fisrt.domain.Member;
import com.boot.fisrt.domain.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Slf4j
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

//        String sql = "INSERT INTO member(login_id, name, password, role) VALUES (?, ?, ?, ?)";
//
//        // insert, update, delete
//        int num = jdbcTemplate.update(sql, member.getLoginId(), member.getName(), member.getPassword() , member.getRole().toString());
//
//        if(num > 0) {
//            log.info("insertMember={}", member);
//        }
        return member;

    }

    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query("select * from member", memberRowMapper());
    }

    @Override
    public Member findOne(Long id) {
        List<Member> list = jdbcTemplate.query("select * from member where id =?", memberRowMapper() , id);
        if(list.size() == 0){
           throw new IllegalStateException("해당 key id 값이 존재하지 않습니다");
        }
        return list.get(0);
    }

    @Override
    public Member findByLoginId(String loginId) {
        System.out.println("loginId = " + loginId);
        List<Member> list = jdbcTemplate.query("select * from member where login_id =?", memberRowMapper() , loginId);
        if(list.size() == 0){
            throw new IllegalStateException("해당 key id 값이 존재하지 않습니다");
        }
        return list.get(0);
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("delete from member where member_id=?" , id);
    }
    private Long insertMember(Member member) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("login_id" , member.getLoginId());
        parameters.put("name", member.getName());
        parameters.put("password",member.getPassword());
        parameters.put("role",member.getRole().toString());
        Number id = jdbcInsert.executeAndReturnKey(parameters);

        return id.longValue();

    }

    private RowMapper<Member> memberRowMapper(){
        return new RowMapper<Member>() {
            @Override
            public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
                Member member = Member.builder().
                        id(rs.getLong("member_id")).
                        loginId(rs.getString("login_id")).
                        password(rs.getString("name")).
                        name(rs.getString("password")).
                        role(Role.valueOf(rs.getString("role")))
                        .build();
                return member;
            }
        };
    }

}
