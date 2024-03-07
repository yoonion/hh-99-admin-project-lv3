package com.sparta.admin.service.teacher;

import com.sparta.admin.dto.lecture.LectureInfoResponseDto;
import com.sparta.admin.dto.teacher.*;
import com.sparta.admin.entity.lecture.Lecture;
import com.sparta.admin.entity.teacher.Teacher;
import com.sparta.admin.repository.LectureRepository;
import com.sparta.admin.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

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
                .orElseThrow(() -> new NoSuchElementException("강사가 존재하지 않습니다."));

        teacher.update(requestDto); // 강사 정보 수정
        return new TeacherUpdateResponseDto(teacher);
    }

    @Override
    public TeacherInfoResponseDto getTeacher(Long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("선택한 강사의 정보가 없습니다."));

        return new TeacherInfoResponseDto(teacher);
    }

    @Override
    public List<TeacherLecturesResponseDto> getTeacherLectures(Long id) {
        List<Lecture> lectures = lectureRepository.findAllByTeacherIdOrderByCreatedAtDesc(id);
        if (lectures.isEmpty()) {
            throw new NoSuchElementException("해당 강의 정보가 없습니다.");
        }

        return lectures.stream()
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
