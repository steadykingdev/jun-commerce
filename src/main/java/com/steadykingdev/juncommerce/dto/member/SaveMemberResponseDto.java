package com.steadykingdev.juncommerce.dto.member;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
public class SaveMemberResponseDto {

    private Long memberId;

    public SaveMemberResponseDto(Long memberId) {
        this.memberId = memberId;
    }
}
