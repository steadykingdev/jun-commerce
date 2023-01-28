package com.steadykingdev.juncommerce.controller;

import com.steadykingdev.juncommerce.dto.ApiResponse;
import com.steadykingdev.juncommerce.dto.member.LoginRequestDto;
import com.steadykingdev.juncommerce.dto.member.MemberResponseDto;
import com.steadykingdev.juncommerce.dto.member.MemberRequestDto;
import com.steadykingdev.juncommerce.service.MemberService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;

    @ApiOperation(value = "회원가입", notes = "회원가입을 한다")
    @PostMapping("/add")
    public ApiResponse save(@Valid @RequestBody MemberRequestDto memberRequestDto) {
        memberService.signup(memberRequestDto);
        return ApiResponse.createSuccessWithNoContent();
    }

    @ApiOperation(value = "회원 조회", notes = "모든 회원을 조회한다.")
    @GetMapping("/list")
    public ApiResponse<List<MemberResponseDto>> getMembers() {
        return ApiResponse.createSuccess(memberService.getMemberList());
    }

    @ApiOperation(value = "회원삭제", notes = "회원을 삭제한다.")
    @DeleteMapping("/delete/{id}")
    public ApiResponse delete(@PathVariable("id") long id) {
        memberService.deleteMember(id);
        return ApiResponse.createSuccessWithNoContent();
    }
}
