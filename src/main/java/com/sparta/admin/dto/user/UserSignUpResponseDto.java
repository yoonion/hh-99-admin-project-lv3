package com.sparta.admin.dto.user;

import com.sparta.admin.entity.User;
import com.sparta.admin.entity.UserRoleEnum;
import lombok.Getter;

@Getter
public class UserSignUpResponseDto {
    private String email;
    private String department;
    private UserRoleEnum role;

    public UserSignUpResponseDto(User user) {
        this.email = user.getEmail();
        this.department = user.getDepartment();
        this.role = user.getRole();
    }
}
