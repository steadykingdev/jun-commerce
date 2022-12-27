package com.steadykingdev.juncommerce.controller;

import com.steadykingdev.juncommerce.dto.ApiResponse;
import com.steadykingdev.juncommerce.dto.member.SaveMemberRequestDto;
import com.steadykingdev.juncommerce.dto.member.SaveMemberResponseDto;
import com.steadykingdev.juncommerce.service.MemberService;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "회원가입", notes = "회원가입을 한다")
    @PostMapping("/add")
    public ApiResponse<SaveMemberResponseDto> save(@Valid @RequestBody SaveMemberRequestDto saveMemberRequestDto) {
        Long memberId = memberService.addMember(saveMemberRequestDto);
        return ApiResponse.createSuccess(new SaveMemberResponseDto(memberId));
    }
}
