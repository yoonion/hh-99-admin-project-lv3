package com.sparta.admin.dto.lecture;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class LectureRegisterRequestDto {
    private Long teacherId;
    private String title;
    private int price;
    private String introduction;
    private String category;
}
