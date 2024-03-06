package com.sparta.admin.service.lecture;

import com.sparta.admin.dto.lecture.*;

import java.util.List;

public interface LectureService {
    LectureRegisterResponseDto registerLecture(LectureRegisterRequestDto requestDto);

    LectureUpdateResponseDto updateLecture(Long id, LectureUpdateRequestDto requestDto);

    LectureInfoResponseDto getLecture(Long id);

    List<LectureInfoResponseDto> getLecturesByCategory(String category);

}
