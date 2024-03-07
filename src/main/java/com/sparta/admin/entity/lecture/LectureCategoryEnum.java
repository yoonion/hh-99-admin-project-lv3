package com.sparta.admin.entity.lecture;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum LectureCategoryEnum {
    SPRING("Spring"),
    REACT("React"),
    NODE("Node");

    private final String categoryName;

    LectureCategoryEnum(String categoryName) {
        this.categoryName = categoryName;
    }

    // 문자열에 맞는 카테고리 찾아오기
    public static LectureCategoryEnum convertStringToCategory(String text) {
        return Arrays.stream(LectureCategoryEnum.values())
                .filter(category -> category.categoryName.equalsIgnoreCase(text))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(text + " 카테고리가 존재하지 않습니다. 다시 입력 해주세요."));
    }
}
