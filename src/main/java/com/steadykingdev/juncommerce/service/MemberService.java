package com.steadykingdev.juncommerce.service;

import com.steadykingdev.juncommerce.dto.member.SaveMemberRequestDto;
import com.steadykingdev.juncommerce.entity.member.Member;
import com.steadykingdev.juncommerce.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Long addMember(SaveMemberRequestDto memberDto) {
        Member member = memberRepository.save(memberDto.toEntity());
        return member.getId();
    }

}
