package com.sparta.admin.controller;

import com.sparta.admin.dto.lecture.*;
import com.sparta.admin.entity.UserRoleEnum;
import com.sparta.admin.service.lecture.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lectures")
public class LectureController {

    private final LectureService lectureService;

    @Secured(UserRoleEnum.Authority.MANAGER)
    @PostMapping
    public ResponseEntity<LectureRegisterResponseDto> registerLecture(@RequestBody LectureRegisterRequestDto requestDto) {
        LectureRegisterResponseDto responseDto = lectureService.registerLecture(requestDto);

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @Secured(UserRoleEnum.Authority.MANAGER)
    @PutMapping("/{id}")
    public ResponseEntity<LectureUpdateResponseDto> updateLecture(
            @PathVariable Long id, @RequestBody LectureUpdateRequestDto requestDto
    ) {
        LectureUpdateResponseDto responseDto = lectureService.updateLecture(id, requestDto);

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LectureInfoResponseDto> getLecture(@PathVariable Long id) {
        LectureInfoResponseDto responseDto = lectureService.getLecture(id);

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
