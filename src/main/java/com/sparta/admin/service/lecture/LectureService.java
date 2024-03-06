package com.sparta.admin.service.lecture;

import com.sparta.admin.dto.lecture.*;

public interface LectureService {
    LectureRegisterResponseDto registerLecture(LectureRegisterRequestDto requestDto);

    LectureUpdateResponseDto updateLecture(Long id, LectureUpdateRequestDto requestDto);

    LectureInfoResponseDto getLecture(Long id);
}
