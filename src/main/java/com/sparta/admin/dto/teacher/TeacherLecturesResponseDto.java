package com.sparta.admin.dto.teacher;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.sparta.admin.entity.lecture.Lecture;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TeacherLecturesResponseDto {
    private final Long id;
    private final String teacher;
    private final String title;
    private final String introduction;
    private final int price;
    private final String category;
    private final LocalDateTime createAt;

    public TeacherLecturesResponseDto(Lecture lecture) {
        this.id = lecture.getId();
        this.teacher = lecture.getTeacher().getName();
        this.title = lecture.getTitle();
        this.introduction = lecture.getIntroduction();
        this.price = lecture.getPrice();
        this.category = lecture.getCategory().getCategoryName();
        this.createAt = lecture.getCreatedAt();
    }
}
