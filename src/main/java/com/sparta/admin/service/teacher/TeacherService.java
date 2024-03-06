package com.sparta.admin.service.teacher;

import com.sparta.admin.dto.teacher.*;

public interface TeacherService {
    TeacherRegisterResponseDto registerTeacher(TeacherRegisterRequestDto requestDto);

    TeacherUpdateResponseDto updateTeacher(Long id, TeacherUpdateRequestDto requestDto);

    TeacherInfoResponseDto getTeacher(Long id);

}
