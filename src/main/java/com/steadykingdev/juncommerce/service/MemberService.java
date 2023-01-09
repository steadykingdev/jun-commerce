package com.steadykingdev.juncommerce.service;

import com.steadykingdev.juncommerce.dto.member.SaveMemberRequestDto;
import com.steadykingdev.juncommerce.entity.member.Member;
import com.steadykingdev.juncommerce.exception.UserNotFoundException;
import com.steadykingdev.juncommerce.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member findMember(Long memberId) {
        return memberExistence(memberId);
    }

    @Transactional
    public Long addMember(SaveMemberRequestDto memberDto) {
        Member member = memberDto.toEntity();
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    @Transactional
    public void deleteMember(Long memberId) {
        memberExistence(memberId);
        memberRepository.deleteById(memberId);
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByLoginId(member.getLoginId());
        System.out.println("member id = " + member.getLoginId());
        System.out.println("findmember = " + findMembers);
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    private Member memberExistence(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new NoSuchElementException("회원이 존재하지 않습니다."));
    }

}
