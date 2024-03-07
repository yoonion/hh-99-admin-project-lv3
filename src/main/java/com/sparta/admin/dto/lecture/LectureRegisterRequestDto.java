package com.sparta.admin.dto.lecture;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class LectureRegisterRequestDto {
    @NotBlank
    private Long teacherId;

    @NotBlank
    private String title;

    @NotNull
    private int price;

    @NotBlank
    private String introduction;

    @NotBlank
    private String category;
}
