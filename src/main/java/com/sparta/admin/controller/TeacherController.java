package com.sparta.admin.controller;

import com.sparta.admin.dto.teacher.*;
import com.sparta.admin.dto.teacher.TeacherUpdateRequestDto;
import com.sparta.admin.entity.UserRoleEnum;
import com.sparta.admin.service.teacher.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    @Secured(UserRoleEnum.Authority.MANAGER) // 관리자용
    @PostMapping
    public ResponseEntity<TeacherRegisterResponseDto> registerTeacher(@RequestBody @Valid TeacherRegisterRequestDto requestDto) {
        TeacherRegisterResponseDto responseDto = teacherService.registerTeacher(requestDto);

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @Secured(UserRoleEnum.Authority.MANAGER) // 관리자용
    @PutMapping("/{id}")
    public ResponseEntity<TeacherUpdateResponseDto> updateTeacher(
            @RequestBody @Valid TeacherUpdateRequestDto requestDto, @PathVariable Long id
    ) {
        TeacherUpdateResponseDto responseDto = teacherService.updateTeacher(id, requestDto);

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
