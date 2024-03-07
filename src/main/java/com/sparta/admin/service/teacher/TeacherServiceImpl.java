package com.sparta.admin.service.teacher;

import com.sparta.admin.dto.teacher.*;
import com.sparta.admin.entity.Teacher;
import com.sparta.admin.repository.LectureRepository;
import com.sparta.admin.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final LectureRepository lectureRepository;

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

    @Override
    public TeacherInfoResponseDto getTeacher(Long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("선택한 강사의 정보가 없습니다."));

        return new TeacherInfoResponseDto(teacher);
    }

    @Override
    public List<TeacherLecturesResponseDto> getTeacherLectures(Long id) {
        return lectureRepository.findAllByTeacherIdOrderByCreatedAtDesc(id)
                .stream()
                .map(TeacherLecturesResponseDto::new)
                .toList();
    }

    @Override
    @Transactional
    public TeacherDeleteResponseDto deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
        return new TeacherDeleteResponseDto(id);
    }
}
