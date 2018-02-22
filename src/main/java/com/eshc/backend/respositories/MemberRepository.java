package com.eshc.backend.respositories;

import com.eshc.backend.models.Member;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

@Transactional(SUPPORTS)
public class MemberRepository {

    @PersistenceContext(name = "eshcPU")
    private EntityManager entityManager;

    @Transactional(REQUIRED)
    public Member createMember(Member member){
        entityManager.persist(member);
        return member;
    }

    public Member getMember(Long id){
        return entityManager.find(Member.class, id);
    }

    @Transactional(REQUIRED)
    public Member updateMember(Member member){
        entityManager.merge(member);
        return member;
    }

    @Transactional(REQUIRED)
    public void deleteMember(Long id){
        entityManager.remove(entityManager.getReference(Member.class, id));
    }

    public List<Member> getMembers(){
        TypedQuery<Member> query = entityManager.createQuery("SELECT m from Member m ORDER BY m.firstName", Member.class);
        return query.getResultList();
    }

    public Long countAllMembers(){
        TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(m) FROM Member m", Long.class);
        return query.getSingleResult();
    }

}
