package com.sparta.admin.dto.user;

import lombok.Getter;

@Getter
public class UserSignUpRequestDto {
    private String email;
    private String password;
    private String department;
}
