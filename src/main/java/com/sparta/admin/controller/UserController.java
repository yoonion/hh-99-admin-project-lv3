package com.sparta.admin.controller;

import com.sparta.admin.dto.user.UserSignUpRequestDto;
import com.sparta.admin.dto.user.UserSignUpResponseDto;
import com.sparta.admin.entity.User;
import com.sparta.admin.security.UserDetailsImpl;
import com.sparta.admin.service.UserService;
import com.sparta.admin.service.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> signUp(@RequestBody @Valid UserSignUpRequestDto requestDto) {
        UserSignUpResponseDto userSignUpResponseDto = userService.signUp(requestDto);

        return ResponseEntity.status(HttpStatus.OK).body(userSignUpResponseDto);
    }

//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody UserLoginRequestDto requestDto, HttpServletResponse response) {
//        userService.login(requestDto, response);
//
//        return ResponseEntity.status(HttpStatus.OK).body("로그인 성공");
//    }

}
