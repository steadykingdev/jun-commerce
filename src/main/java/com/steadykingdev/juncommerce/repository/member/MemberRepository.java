package com.steadykingdev.juncommerce.repository.member;

import com.steadykingdev.juncommerce.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {
}
