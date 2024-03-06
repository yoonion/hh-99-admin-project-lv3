package com.sparta.admin.service.teacher;

import com.sparta.admin.dto.teacher.TeacherRegisterRequestDto;
import com.sparta.admin.dto.teacher.TeacherRegisterResponseDto;
import com.sparta.admin.dto.teacher.TeacherUpdateRequestDto;
import com.sparta.admin.dto.teacher.TeacherUpdateResponseDto;

public interface TeacherService {
    TeacherRegisterResponseDto registerTeacher(TeacherRegisterRequestDto requestDto);

    TeacherUpdateResponseDto updateTeacher(Long id, TeacherUpdateRequestDto requestDto);
}
