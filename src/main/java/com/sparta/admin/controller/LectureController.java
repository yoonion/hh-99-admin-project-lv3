package com.sparta.admin.controller;

import com.sparta.admin.dto.lecture.LectureRegisterRequestDto;
import com.sparta.admin.dto.lecture.LectureRegisterResponseDto;
import com.sparta.admin.entity.UserRoleEnum;
import com.sparta.admin.service.lecture.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lectures")
public class LectureController {

    private final LectureService lectureService;

    @Secured(UserRoleEnum.Authority.MANAGER) // 관리자용
    @PostMapping
    public ResponseEntity<LectureRegisterResponseDto> registerLecture(@RequestBody LectureRegisterRequestDto requestDto) {
        LectureRegisterResponseDto responseDto = lectureService.registerLecture(requestDto);

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
