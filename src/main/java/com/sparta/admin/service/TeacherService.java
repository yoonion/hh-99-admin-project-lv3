package com.sparta.admin.service;

import com.sparta.admin.dto.teacher.TeacherRegisterResponseDto;
import com.sparta.admin.dto.teacher.TeacherRegisterRequestDto;
import com.sparta.admin.entity.Teacher;
import com.sparta.admin.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherRegisterResponseDto registerTeacher(TeacherRegisterRequestDto requestDto) {
        Teacher teacher = new Teacher(requestDto);
        teacherRepository.save(teacher);

        return new TeacherRegisterResponseDto(teacher);
    }
}
