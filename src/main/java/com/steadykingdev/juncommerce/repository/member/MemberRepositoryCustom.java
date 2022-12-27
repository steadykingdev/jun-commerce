package com.steadykingdev.juncommerce.repository.member;

import com.steadykingdev.juncommerce.entity.member.Member;

public interface MemberRepositoryCustom {

    public Member findByLoginId(Long loginId);
}
