package com.sparta.admin.dto.teacher;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.sparta.admin.entity.Teacher;
import lombok.Getter;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TeacherInfoResponseDto {
    private final Long id;
    private final String name;
    private final int career;
    private final String company;
    private final String phoneNumber;
    private final String introduction;

    public TeacherInfoResponseDto(Teacher teacher) {
        this.id = teacher.getId();
        this.name = teacher.getName();
        this.career = teacher.getCareer();
        this.company = teacher.getCompany();
        this.phoneNumber = teacher.getPhoneNumber();
        this.introduction = teacher.getIntroduction();
    }
}
