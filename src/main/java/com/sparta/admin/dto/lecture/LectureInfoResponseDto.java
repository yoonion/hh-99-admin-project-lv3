package com.sparta.admin.dto.lecture;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.sparta.admin.entity.Lecture;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class LectureInfoResponseDto {
    private final Long id;
    private final String teacher;
    private final String title;
    private final int price;
    private final String introduction;
    private final String category;
    private final LocalDateTime createdAt;

    public LectureInfoResponseDto(Lecture lecture) {
        this.id = lecture.getId();
        this.teacher = lecture.getTeacher().getName();
        this.title = lecture.getTitle();
        this.price = lecture.getPrice();
        this.introduction = lecture.getIntroduction();
        this.category = lecture.getCategory();
        this.createdAt = lecture.getCreatedAt();
    }
}
