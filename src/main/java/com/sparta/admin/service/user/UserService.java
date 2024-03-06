package com.sparta.admin.service.user;

import com.sparta.admin.dto.user.UserSignUpRequestDto;
import com.sparta.admin.dto.user.UserSignUpResponseDto;

public interface UserService {
    UserSignUpResponseDto signUp(UserSignUpRequestDto requestDto);
}
