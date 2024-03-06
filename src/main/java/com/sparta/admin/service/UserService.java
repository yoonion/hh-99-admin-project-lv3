package com.sparta.admin.service;

import com.sparta.admin.dto.user.UserLoginRequestDto;
import com.sparta.admin.dto.user.UserSignUpRequestDto;
import com.sparta.admin.dto.user.UserSignUpResponseDto;
import com.sparta.admin.entity.User;
import com.sparta.admin.entity.UserRoleEnum;
import com.sparta.admin.jwt.JwtUtils;
import com.sparta.admin.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public UserSignUpResponseDto signUp(UserSignUpRequestDto requestDto) {
        String email = requestDto.getEmail();
        String password = passwordEncoder.encode(requestDto.getPassword()); // 암호화
        String department = requestDto.getDepartment();

        // 권한 설정
        UserRoleEnum role;
        if (department.equals("커리큘럼") || department.equals("개발")) {
            role = UserRoleEnum.MANAGER;
        } else {
            role = UserRoleEnum.STAFF;
        }

        User user = new User(email, password, department, role);
        userRepository.save(user);

        return new UserSignUpResponseDto(user);
    }

//    public void login(UserLoginRequestDto requestDto, HttpServletResponse response) {
//        String email = requestDto.getEmail();
//        String password = requestDto.getPassword();
//
//        // 사용자 확인
//        User user = userRepository.findByEmail(email)
//                .orElseThrow(() -> new IllegalArgumentException("회원 정보가 없습니다."));
//
//        // 비밀번호 확인
//        if (!passwordEncoder.matches(password, user.getPassword())) {
//            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
//        }
//
//        // JWT 생성 및 쿠키에 추가
//        String token = jwtUtils.createToken(user.getEmail(), user.getRole());
//        jwtUtils.addJwtToCookie(token, response);
//    }
}
