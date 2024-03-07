package com.sparta.admin.dto.lecture;

import com.sparta.admin.entity.Lecture;
import lombok.Getter;

@Getter
public class LectureRegisterResponseDto {
    private final String teacher;
    private final String title;
    private final int price;
    private final String introduction;
    private final String category;

    public LectureRegisterResponseDto(Lecture lecture) {
        this.teacher = lecture.getTeacher().getName();
        this.title = lecture.getTitle();
        this.price = lecture.getPrice();
        this.introduction = lecture.getIntroduction();
        this.category = lecture.getCategory().getCategoryName();

    }
}
