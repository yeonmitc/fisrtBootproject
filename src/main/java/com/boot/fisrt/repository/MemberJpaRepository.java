package com.boot.fisrt.repository;

import com.boot.fisrt.domain.Member;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
@Slf4j
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
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    @Override
    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    @Override
    public Member findByLoginId(String loginId) {
        List<Member> members =  em.createQuery("select m from Member m where m.loginId = :loginId", Member.class)
                .setParameter("loginId", loginId)
                .getResultList();

        return members.stream().findAny().orElse(null);

    }

    @Override
    public void delete(Long id) {
        int deletedCount = em.createQuery(
                "delete from Member m where m.id =:id").setParameter("id",id).executeUpdate();
        if(deletedCount > 0) {
            log.info("member id={} 삭제성공" , id);
        }
    }
}
