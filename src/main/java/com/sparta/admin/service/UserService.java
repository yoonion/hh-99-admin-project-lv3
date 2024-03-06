package com.sparta.admin.service;

import com.sparta.admin.dto.user.UserSignUpRequestDto;
import com.sparta.admin.dto.user.UserSignUpResponseDto;

public interface UserService {
    UserSignUpResponseDto signUp(UserSignUpRequestDto requestDto);
}
