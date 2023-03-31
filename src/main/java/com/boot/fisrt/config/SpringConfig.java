package com.boot.fisrt.config;

import com.boot.fisrt.repository.MemberJdbcRepository;
import com.boot.fisrt.repository.MemberListRepository;
import com.boot.fisrt.repository.MemberRepository;
import com.boot.fisrt.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    // 수동으로 스프링빈 컨테이너에 저장
    private final DataSource dataSource;
    public SpringConfig(DataSource dataSource){
        this.dataSource = dataSource;
    }
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository(){
        //return new MemberListRepository();
       return new MemberJdbcRepository(dataSource);
    }
}
