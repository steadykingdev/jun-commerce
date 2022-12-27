package com.steadykingdev.juncommerce.repository.member;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.steadykingdev.juncommerce.entity.member.Member;
import com.steadykingdev.juncommerce.repository.item.ItemRepository;

import javax.persistence.EntityManager;

public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public MemberRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public Member findByLoginId(Long loginId) {
        return null;
    }
}
