package com.steadykingdev.juncommerce.service;

import com.steadykingdev.juncommerce.dto.member.SaveMemberRequestDto;
import com.steadykingdev.juncommerce.entity.Address;
import com.steadykingdev.juncommerce.entity.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    public void addMember() throws Exception {
        //given
        Address address = new Address("서울시", "시흥대로 23-7", "202호");
        SaveMemberRequestDto memberDto = new SaveMemberRequestDto("GOAT", "만준", "test12!@", "test12!@", address);

        //when
        Long memberId = memberService.addMember(memberDto);

        //then
        Assertions.assertThat(memberId).isEqualTo(4L);
    }


}