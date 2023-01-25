package com.steadykingdev.juncommerce.dto.member;

import com.querydsl.core.annotations.QueryProjection;
import com.steadykingdev.juncommerce.entity.Address;
import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Setter(AccessLevel.NONE)
public class MemberResponseDto {

    private Long id;

    private String loginId;

    private String username;

    private Address address;

    @QueryProjection
    public MemberResponseDto(Long id, String loginId, String username, Address address) {
        this.id = id;
        this.loginId = loginId;
        this.username = username;
        this.address = address;
    }
}
