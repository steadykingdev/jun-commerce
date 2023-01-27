package com.steadykingdev.juncommerce.dto.member;

import com.steadykingdev.juncommerce.entity.Address;
import com.steadykingdev.juncommerce.entity.member.Authority;
import com.steadykingdev.juncommerce.entity.member.Member;
import lombok.*;

import java.util.Set;

@Getter
@Builder
public class MemberResponseDto {

    private Long id;

    private String loginId;

    private String username;

    private Set<Authority> authorities;

    private Address address;

    public MemberResponseDto(Long id, String loginId, String username, Set<Authority> authorities, Address address) {
        this.id = id;
        this.loginId = loginId;
        this.username = username;
        this.authorities = authorities;
        this.address = address;
    }

    public static MemberResponseDto fromEntity(Member member) {
        return new MemberResponseDto(member.getId(), member.getLoginId(), member.getUsername(), member.getAuthorities(), member.getAddress());
    }
}
