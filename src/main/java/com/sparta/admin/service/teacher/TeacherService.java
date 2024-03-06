package com.sparta.admin.service.teacher;

import com.sparta.admin.dto.teacher.*;

import java.util.List;

public interface TeacherService {
    TeacherRegisterResponseDto registerTeacher(TeacherRegisterRequestDto requestDto);

    TeacherUpdateResponseDto updateTeacher(Long id, TeacherUpdateRequestDto requestDto);

    TeacherInfoResponseDto getTeacher(Long id);

    List<TeacherLecturesResponseDto> getTeacherLectures(Long id);
}
