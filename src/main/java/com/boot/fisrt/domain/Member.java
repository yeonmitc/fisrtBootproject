package com.boot.fisrt.domain;

import lombok.*;

import javax.persistence.*;


@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id; // wrapper class long -> 디폴트 값이 0 이 아니라 null 을 주기 위해서 // int가 아니라 Integer
    private String loginId;
    private String password;
    private String name;
    @Enumerated(EnumType.STRING)
    private Role role;

    // 빌더에 필수 파라미터값 셋팅하기 -> memberlistRepository
//    public static MemberBuilder builder(Long id){
//        if(id == null) throw new IllegalArgumentException("필수 파라미터 누락");
//        return new MemberBuilder().id(id);
//    }

    public void setKeyId(Long key){
        this.id = key;
    }
    @Builder
    public Member(Long id,String loginId, String password, String name, Role role) {
        this.id = id;
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.role = role;
    }




}
