package com.sparta.admin.service.lecture;

import com.sparta.admin.dto.lecture.*;
import com.sparta.admin.entity.LectureCategoryEnum;

import java.util.List;

public interface LectureService {
    LectureRegisterResponseDto registerLecture(LectureRegisterRequestDto requestDto);

    LectureUpdateResponseDto updateLecture(Long id, LectureUpdateRequestDto requestDto);

    LectureInfoResponseDto getLecture(Long id);

    List<LectureInfoResponseDto> getLecturesByCategory(LectureCategoryEnum category);

    Long deleteLecture(Long id);
}
