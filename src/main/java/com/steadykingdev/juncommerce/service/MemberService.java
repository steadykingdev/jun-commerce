package com.steadykingdev.juncommerce.service;

import com.steadykingdev.juncommerce.dto.member.MemberRequestDto;
import com.steadykingdev.juncommerce.dto.member.MemberResponseDto;
import com.steadykingdev.juncommerce.entity.member.Authority;
import com.steadykingdev.juncommerce.entity.member.Member;
import com.steadykingdev.juncommerce.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signup(MemberRequestDto memberDto) {
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

    @Transactional
    public void signupAdmin(MemberRequestDto memberDto) {

        Set<Authority> authoritySet = new HashSet<>();

        validateMember(memberDto);

        Authority authority1 = Authority.builder()
                .authorityName("ROLE_ADMIN")
                .build();

        Authority authority2 = Authority.builder()
                .authorityName("ROLE_USER")
                .build();
        authoritySet.add(authority1);
        authoritySet.add(authority2);

        Member member = Member.builder()
                .loginId(memberDto.getLoginId())
                .username(memberDto.getUsername())
                .password(passwordEncoder.encode(memberDto.getPassword()))
                .authorities(authoritySet)
                .address(memberDto.getAddress())
                .build();

        memberRepository.save(member);
    }

    public List<MemberResponseDto> getMemberList() {
        List<Member> members = memberRepository.findAll();

        return members.stream()
                .map(m -> MemberResponseDto.fromEntity(m))
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteMember(long id) {

        if (memberRepository.existsById(id)) {
            memberRepository.deleteById(id);
        }
    }

    public Member getMemberWithAuthorities(String loginId) {

        return memberRepository.findOneWithAuthoritiesByLoginId(loginId).orElseThrow(() -> new RuntimeException("회원이 존재하지 않습니다."));
    }

    private void validateMember(MemberRequestDto memberDto) {

        if (memberRepository.findOneWithAuthoritiesByLoginId(memberDto.getLoginId()).orElse(null) != null) {
            throw new RuntimeException("이미 존재하는 회원입니다.");
        } else if (!memberDto.getPassword().equals(memberDto.getPasswordCheck())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }
    }

    private Member memberExistence(long memberId) {

        return memberRepository.findById(memberId).orElseThrow(() -> new RuntimeException("회원이 존재하지 않습니다."));
    }

}
