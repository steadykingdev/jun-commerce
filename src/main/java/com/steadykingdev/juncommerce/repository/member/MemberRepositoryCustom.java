package com.steadykingdev.juncommerce.repository.member;

import com.steadykingdev.juncommerce.entity.member.Member;

import java.util.List;

public interface MemberRepositoryCustom {

    public List<Member> findByLoginId(String loginId);
}
