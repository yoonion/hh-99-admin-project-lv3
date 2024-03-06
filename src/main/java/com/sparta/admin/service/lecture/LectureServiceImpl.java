package com.sparta.admin.service.lecture;

import com.sparta.admin.dto.lecture.*;
import com.sparta.admin.entity.Lecture;
import com.sparta.admin.entity.Teacher;
import com.sparta.admin.repository.LectureRepository;
import com.sparta.admin.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

    @Override
    @Transactional
    public LectureUpdateResponseDto updateLecture(Long id, LectureUpdateRequestDto requestDto) {
        Lecture lecture = lectureRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("수정할 강의 정보가 존재하지 않습니다."));

        lecture.update(requestDto);

        return new LectureUpdateResponseDto(lecture);
    }

    @Override
    public LectureInfoResponseDto getLecture(Long id) {
        Lecture lecture = lectureRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 강의 정보가 존재하지 않습니다."));

        return new LectureInfoResponseDto(lecture);
    }
}
