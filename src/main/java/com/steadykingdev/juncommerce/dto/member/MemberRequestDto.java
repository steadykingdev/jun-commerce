package com.steadykingdev.juncommerce.dto.member;

import com.steadykingdev.juncommerce.entity.Address;
import com.steadykingdev.juncommerce.entity.member.Authority;
import com.steadykingdev.juncommerce.entity.member.Member;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Setter(AccessLevel.NONE)
public class MemberRequestDto {

    @NotBlank(message = "아이디를 입력하세요")
    @Size(min = 2, max = 15, message = "아이디는 2 ~ 15자로 입력해주세요.")
    private String loginId;

    @NotBlank(message = "이름을 입력하세요")
    @Size(min = 2, max = 15, message = "닉네임은 2 ~ 15자로 입력해주세요.")
    private String username;

    @NotBlank(message = "비밀번호를 입력하세요")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$", message = "8자 이상 16자 이하의 숫자, 영문자, 특수문자를 포함한 비밀번호를 입력해주세요.")
    private String password;

    @NotBlank(message = "비밀번호를 입력하세요")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$", message = "8자 이상 16자 이하의 숫자, 영문자, 특수문자를 포함한 비밀번호를 입력해주세요.")
    private String passwordCheck;

    private Address address;

    public MemberRequestDto(String loginId, String username, String password, String passwordCheck, Address address) {
        this.loginId = loginId;
        this.username = username;
        this.password = password;
        this.passwordCheck = passwordCheck;
        this.address = address;
    }

    public Member toEntity() {
        return Member.builder()
                .loginId(loginId)
                .username(username)
                .password(password)
                .address(address)
                .build();
    }
}
