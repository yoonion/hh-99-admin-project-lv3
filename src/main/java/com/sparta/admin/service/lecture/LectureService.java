package com.sparta.admin.service.lecture;

import com.sparta.admin.dto.lecture.LectureRegisterRequestDto;
import com.sparta.admin.dto.lecture.LectureRegisterResponseDto;
import com.sparta.admin.dto.lecture.LectureUpdateRequestDto;
import com.sparta.admin.dto.lecture.LectureUpdateResponseDto;

public interface LectureService {
    LectureRegisterResponseDto registerLecture(LectureRegisterRequestDto requestDto);

    LectureUpdateResponseDto updateLecture(Long id, LectureUpdateRequestDto requestDto);

}
