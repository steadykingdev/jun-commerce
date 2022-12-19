package com.steadykingdev.juncommerce.controller;

import com.steadykingdev.juncommerce.dto.ApiResponse;
import com.steadykingdev.juncommerce.dto.member.SaveMemberRequestDto;
import com.steadykingdev.juncommerce.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/add")
    public ApiResponse save(@Valid @RequestBody SaveMemberRequestDto saveMemberRequestDto) {
        memberService.addMember(saveMemberRequestDto);
        return ApiResponse.createSuccessWithNoContent();
    }
}
