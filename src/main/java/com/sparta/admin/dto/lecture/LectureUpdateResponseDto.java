package com.sparta.admin.dto.lecture;

import com.sparta.admin.entity.Lecture;
import lombok.Getter;

@Getter
public class LectureUpdateResponseDto {
    private String title;
    private int price;
    private String introduction;
    private String category;

    public LectureUpdateResponseDto(Lecture lecture) {
        this.title = lecture.getTitle();
        this.price = lecture.getPrice();
        this.introduction = lecture.getIntroduction();
        this.category = lecture.getCategory();
    }
}
