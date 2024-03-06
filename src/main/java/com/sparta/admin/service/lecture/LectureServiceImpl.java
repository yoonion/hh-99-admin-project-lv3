package com.sparta.admin.service.lecture;

import com.sparta.admin.dto.lecture.LectureRegisterRequestDto;
import com.sparta.admin.dto.lecture.LectureRegisterResponseDto;
import com.sparta.admin.entity.Lecture;
import com.sparta.admin.entity.Teacher;
import com.sparta.admin.repository.LectureRepository;
import com.sparta.admin.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LectureServiceImpl implements LectureService {

    private final LectureRepository lectureRepository;
    private final TeacherRepository teacherRepository;

    @Override
    @Transactional
    public LectureRegisterResponseDto registerLecture(LectureRegisterRequestDto requestDto) {
        Long teacherId = requestDto.getTeacherId();
        Teacher findTeacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new IllegalArgumentException("해당 강사가 존재하지 않습니다."));

        Lecture lecture = new Lecture(findTeacher, requestDto);
        lectureRepository.save(lecture);

        return new LectureRegisterResponseDto(lecture);
    }
}
