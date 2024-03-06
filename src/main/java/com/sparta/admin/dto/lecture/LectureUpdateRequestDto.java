package com.sparta.admin.dto.lecture;

import lombok.Getter;

@Getter
public class LectureUpdateRequestDto {
    private String title;
    private int price;
    private String introduction;
    private String category;
}
