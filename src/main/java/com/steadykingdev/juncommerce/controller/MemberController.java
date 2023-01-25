package com.steadykingdev.juncommerce.controller;

import com.steadykingdev.juncommerce.dto.ApiResponse;
import com.steadykingdev.juncommerce.dto.member.LoginRequestDto;
import com.steadykingdev.juncommerce.dto.member.MemberResponseDto;
import com.steadykingdev.juncommerce.dto.member.SaveMemberRequestDto;
import com.steadykingdev.juncommerce.dto.member.SaveMemberResponseDto;
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

    @ApiOperation(value = "회원 조회", notes = "모든 회원을 조회한다.")
    @GetMapping("/list")
    public ApiResponse<List<MemberResponseDto>> getMembers() {
        List<MemberResponseDto> members = memberService.findMembers();
        return ApiResponse.createSuccess(members);
    }

    @ApiOperation(value = "회원가입", notes = "회원가입을 한다")
    @PostMapping("/add")
    public ApiResponse<SaveMemberResponseDto> save(@Valid @RequestBody SaveMemberRequestDto saveMemberRequestDto) {
        Long memberId = memberService.addMember(saveMemberRequestDto);
        return ApiResponse.createSuccess(new SaveMemberResponseDto(memberId));
    }

    @ApiOperation(value = "회원삭제", notes = "회원을 삭제한다.")
    @DeleteMapping("/delete/{id}")
    public ApiResponse delete(@PathVariable("id") Long id) {
        memberService.deleteMember(id);
        return ApiResponse.createSuccessWithNoContent();
    }

    @ApiOperation(value = "로그인", notes = "로그인을 한다.")
    @PostMapping("/login")
    public ApiResponse login(@RequestBody LoginRequestDto loginRequestDto) {

        return ApiResponse.createSuccessWithNoContent();
    }
}
