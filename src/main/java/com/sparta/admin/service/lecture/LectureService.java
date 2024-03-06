package com.sparta.admin.service.lecture;

import com.sparta.admin.dto.lecture.LectureRegisterRequestDto;
import com.sparta.admin.dto.lecture.LectureRegisterResponseDto;

public interface LectureService {
    LectureRegisterResponseDto registerLecture(LectureRegisterRequestDto requestDto);
}
