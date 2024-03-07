package com.sparta.admin.dto.teacher;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TeacherUpdateRequestDto {
    @NotBlank
    private String name;

    @NotNull
    private int career;

    @NotBlank
    private String company;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String introduction;
}
