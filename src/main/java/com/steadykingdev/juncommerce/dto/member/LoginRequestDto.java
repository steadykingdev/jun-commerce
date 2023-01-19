package com.steadykingdev.juncommerce.dto.member;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequestDto {

    @NotBlank
    private String loginId;

    @NotBlank
    private String password;
}
