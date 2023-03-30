package com.boot.fisrt.repository;

import com.boot.fisrt.domain.Member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//DAO list
public class MemberListRepository  implements MemberRepository{
// ctrl + shfit + t : 테스트 클래스를 만들어줌
    private static Map<Long,Member> store = new HashMap<>();
    // id 자동증가하는 아이디값
    private static long sequence =1L;

    public static long getSequence() {
        return sequence++;
    }

    @Override
    public Member save(Member member) {
       // member.setId(sequence++);
        store.put(member.getId(),member);
        return member;
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Member findOne(Long id) {
        return store.get(id); // 값이 없으면 null 리턴
    }

    @Override
    public Member findByLoginId(String loginId) {
        // shift + ctrl + alt + t
        for(Member member : store.values()){
            if(member.getLoginId().equals(loginId)){
                return member;
            }
        }
        //Member member2 = store.values().stream().filter(m->m.getId().equals(loginId)).findAny().get();
        return null;
    }

    @Override
    public void delete(Member member) {
        store.remove(member.getId());
    }

    public void clearStore(){
        store.clear();
    }
}
