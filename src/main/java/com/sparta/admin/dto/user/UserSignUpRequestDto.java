package com.sparta.admin.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserSignUpRequestDto {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 8, max = 15,
            message = "비밀번호는 8자 이상, 15자 이하로 입력하여야 하며, 알파벳 대소문자, 숫자, 특수문자를 포함하여야 합니다.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$",
            message = "비밀번호는 8자 이상, 15자 이하로 입력하여야 하며, 알파벳 대소문자, 숫자, 특수문자를 포함하여야 합니다.")
    private String password;

    @NotBlank
    private String department;
}
