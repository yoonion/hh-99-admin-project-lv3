package com.sparta.admin.service.teacher;

import com.sparta.admin.dto.teacher.TeacherRegisterResponseDto;
import com.sparta.admin.dto.teacher.TeacherRegisterRequestDto;
import com.sparta.admin.dto.teacher.TeacherUpdateRequestDto;
import com.sparta.admin.dto.teacher.TeacherUpdateResponseDto;
import com.sparta.admin.entity.Teacher;
import com.sparta.admin.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    @Override
    @Transactional
    public TeacherRegisterResponseDto registerTeacher(TeacherRegisterRequestDto requestDto) {
        Teacher teacher = new Teacher(requestDto);
        teacherRepository.save(teacher);

        return new TeacherRegisterResponseDto(teacher);
    }

    @Override
    @Transactional
    public TeacherUpdateResponseDto updateTeacher(Long id, TeacherUpdateRequestDto requestDto) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("강사가 존재하지 않습니다."));

        teacher.update(requestDto); // 강사 정보 수정
        return new TeacherUpdateResponseDto(teacher);
    }
}
