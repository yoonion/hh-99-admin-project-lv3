package com.sparta.admin.dto.lecture;

import com.sparta.admin.entity.Lecture;
import com.sparta.admin.entity.LectureCategoryEnum;
import lombok.Getter;

import static com.sparta.admin.entity.LectureCategoryEnum.convertStringToCategory;


@Getter
public class LectureUpdateResponseDto {
    private final String title;
    private final int price;
    private final String introduction;
    private final LectureCategoryEnum category;

    public LectureUpdateResponseDto(Lecture lecture) {
        this.title = lecture.getTitle();
        this.price = lecture.getPrice();
        this.introduction = lecture.getIntroduction();
        this.category = convertStringToCategory(lecture.getCategory().getCategoryName());
    }
}
