package com.sparta.admin.service.lecture;

import com.sparta.admin.dto.lecture.*;
import com.sparta.admin.dto.teacher.TeacherLecturesResponseDto;
import com.sparta.admin.entity.lecture.Lecture;
import com.sparta.admin.entity.lecture.LectureCategoryEnum;
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
public class LectureServiceImpl implements LectureService {

    private final LectureRepository lectureRepository;
    private final TeacherRepository teacherRepository;

    @Override
    @Transactional
    public LectureRegisterResponseDto registerLecture(LectureRegisterRequestDto requestDto) {
        Long teacherId = requestDto.getTeacherId();
        Teacher findTeacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new NoSuchElementException("해당 강사가 존재하지 않습니다."));

        Lecture lecture = new Lecture(findTeacher, requestDto);
        lectureRepository.save(lecture);

        return new LectureRegisterResponseDto(lecture);
    }

    @Override
    @Transactional
    public LectureUpdateResponseDto updateLecture(Long id, LectureUpdateRequestDto requestDto) {
        Lecture lecture = lectureRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("수정할 강의 정보가 존재하지 않습니다."));

        lecture.update(requestDto);

        return new LectureUpdateResponseDto(lecture);
    }

    @Override
    public LectureInfoResponseDto getLecture(Long id) {
        Lecture lecture = lectureRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("해당 강의 정보가 존재하지 않습니다."));

        return new LectureInfoResponseDto(lecture);
    }

    @Override
    public List<LectureInfoResponseDto> getLecturesByCategory(LectureCategoryEnum category) {
        List<Lecture> lectures = lectureRepository.findAllByCategoryOrderByCreatedAtDesc(category);
        if (lectures.isEmpty()) {
            throw new NoSuchElementException("해당 강의 정보가 없습니다.");
        }

        return lectures.stream()
                .map(LectureInfoResponseDto::new)
                .toList();
    }

    @Override
    @Transactional
    public LectureDeleteResponseDto deleteLecture(Long id) {
        lectureRepository.deleteById(id);
        return new LectureDeleteResponseDto(id);
    }
}
