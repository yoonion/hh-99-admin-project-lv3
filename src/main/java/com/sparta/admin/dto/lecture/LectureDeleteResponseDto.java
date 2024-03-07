package com.sparta.admin.dto.lecture;

import lombok.Getter;

@Getter
public class LectureDeleteResponseDto {
    private Long id;

    public LectureDeleteResponseDto(Long id) {
        this.id = id;
    }
}
