package com.steadykingdev.juncommerce.repository.member;

import com.steadykingdev.juncommerce.entity.member.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @EntityGraph(attributePaths = "authorities") // Lazy조회가 아니고 Eager조회로 가져온다.
    Optional<Member> findOneWithAuthoritiesByLoginId(String loginId);
}
