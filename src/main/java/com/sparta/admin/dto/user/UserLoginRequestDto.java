package com.sparta.admin.dto.user;

import lombok.Getter;

@Getter
public class UserLoginRequestDto {
    private String email;
    private String password;
}
