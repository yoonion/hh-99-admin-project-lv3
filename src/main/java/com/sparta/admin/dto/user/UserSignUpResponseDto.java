package com.sparta.admin.dto.user;

import com.sparta.admin.entity.User;
import com.sparta.admin.entity.UserRoleEnum;
import lombok.Getter;

@Getter
public class UserSignUpResponseDto {
    private final String email;
    private final String department;
    private final UserRoleEnum role;

    public UserSignUpResponseDto(User user) {
        this.email = user.getEmail();
        this.department = user.getDepartment();
        this.role = user.getRole();
    }
}
