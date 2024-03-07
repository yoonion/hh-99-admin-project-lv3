package com.sparta.admin.service.user;

import com.sparta.admin.dto.user.UserSignUpRequestDto;
import com.sparta.admin.dto.user.UserSignUpResponseDto;
import com.sparta.admin.entity.user.User;
import com.sparta.admin.entity.user.UserRoleEnum;
import com.sparta.admin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserSignUpResponseDto signUp(UserSignUpRequestDto requestDto) {
        String email = requestDto.getEmail();
        String password = passwordEncoder.encode(requestDto.getPassword()); // 암호화
        String department = requestDto.getDepartment();

        // 이메일 중복 체크
        Optional<User> findUser = userRepository.findByEmail(email);
        if (findUser.isPresent()) {
            throw new IllegalArgumentException("중복된 회원 입니다.");
        }

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
}
