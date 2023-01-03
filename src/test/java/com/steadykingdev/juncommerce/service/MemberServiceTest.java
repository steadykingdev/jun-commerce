package com.steadykingdev.juncommerce.service;

import com.steadykingdev.juncommerce.dto.member.SaveMemberRequestDto;
import com.steadykingdev.juncommerce.entity.Address;
import com.steadykingdev.juncommerce.entity.item.Item;
import com.steadykingdev.juncommerce.entity.member.Member;
import com.steadykingdev.juncommerce.exception.UserNotFoundException;
import com.steadykingdev.juncommerce.repository.member.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @PersistenceContext
    EntityManager em;

    @BeforeEach
    public void before() {
        Address address1 = new Address("서울", "시흥대로", "1-2");
        Member member1 = new Member(null, "Kim", "김", "1234", address1);
        em.persist(member1);
    }

    @Test
    public void addMember() throws Exception {
        //given
        Address address = new Address("서울시", "시흥대로 23-7", "202호");
        SaveMemberRequestDto memberDto = new SaveMemberRequestDto("GOAT", "만준", "test12!@", "test12!@", address);

        //when
        Long memberId = memberService.addMember(memberDto);

        //then
        assertThat(memberId).isEqualTo(5L);
    }

    @Test
    public void addMemberDuplicateException() throws Exception {

        Address address2 = new Address("서울22", "시흥대로11", "1-22");
        SaveMemberRequestDto saveMemberResponseDto = new SaveMemberRequestDto("Kim", "김맨", "1111", "1111", address2);

        assertThatThrownBy(() -> memberService.addMember(saveMemberResponseDto))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("이미 존재하는 회원입니다.");
    }

    @Test
    public void deleteMember() throws Exception {
        //given

        Address address2 = new Address("서울22", "시흥대로11", "1-22");
        SaveMemberRequestDto saveMemberResponseDto = new SaveMemberRequestDto("Lee", "김맨", "1111", "1111", address2);
        Long savedMemberId = memberService.addMember(saveMemberResponseDto);

        //when
        memberService.deleteMember(savedMemberId);

        //then
        assertThatThrownBy(() -> memberService.findMember(savedMemberId))
                .isInstanceOf(UserNotFoundException.class);
    }

    @Test
    public void deleteMemberNotFoundExceptionTest() throws Exception {
        //given
        Address address2 = new Address("서울22", "시흥대로11", "1-22");
        SaveMemberRequestDto saveMemberResponseDto = new SaveMemberRequestDto("Lee", "김맨", "1111", "1111", address2);
        Long savedMemberId = memberService.addMember(saveMemberResponseDto);

        //when
        memberService.deleteMember(savedMemberId);

        //then
        assertThatThrownBy(() -> memberService.deleteMember(savedMemberId))
                .isInstanceOf(UserNotFoundException.class);
    }






}