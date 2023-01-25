package com.steadykingdev.juncommerce.repository.member;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.steadykingdev.juncommerce.dto.member.MemberResponseDto;
import com.steadykingdev.juncommerce.dto.member.QMemberResponseDto;
import com.steadykingdev.juncommerce.entity.member.Member;
import com.steadykingdev.juncommerce.entity.member.QMember;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

import static com.steadykingdev.juncommerce.entity.member.QMember.member;

public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public MemberRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public List<Member> findByLoginId(String loginId) {
        return queryFactory.selectFrom(member)
                .where(member.loginId.eq(loginId))
                .fetch();
    }

    @Override
    public List<MemberResponseDto> findAllMembers() {
        return queryFactory
                .select(new QMemberResponseDto(member.id, member.loginId, member.username, member.address))
                .from(member)
                .fetch();
    }
}
