package com.steadykingdev.juncommerce.service;

import com.steadykingdev.juncommerce.dto.member.MemberDto;
import com.steadykingdev.juncommerce.entity.member.Authority;
import com.steadykingdev.juncommerce.entity.member.Member;
import com.steadykingdev.juncommerce.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signup(MemberDto memberDto) {
        validateMember(memberDto);

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        Member member = Member.builder()
                .loginId(memberDto.getLoginId())
                .username(memberDto.getUsername())
                .password(passwordEncoder.encode(memberDto.getPassword()))
                .authorities(Collections.singleton(authority))
                .address(memberDto.getAddress())
                .build();

        memberRepository.save(member);
    }

    public Member getMemberWithAuthorities(String loginId) {
        return memberRepository.findOneWithAuthoritiesByLoginId(loginId).orElseThrow(() -> new RuntimeException("회원이 존재하지 않습니다."));
    }

    private void validateMember(MemberDto memberDto) {
        if (memberRepository.findOneWithAuthoritiesByLoginId(memberDto.getLoginId()).orElse(null) != null) {
            throw new RuntimeException("이미 존재하는 회원입니다.");
        } else if (!memberDto.getPassword().equals(memberDto.getPasswordCheck())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }
    }

    private Member memberExistence(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new RuntimeException("회원이 존재하지 않습니다."));
    }

}
