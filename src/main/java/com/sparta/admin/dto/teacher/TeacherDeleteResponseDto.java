package com.sparta.admin.dto.teacher;

import lombok.Getter;

@Getter
public class TeacherDeleteResponseDto {
    private final Long id;

    public TeacherDeleteResponseDto(Long id) {
        this.id = id;
    }
}