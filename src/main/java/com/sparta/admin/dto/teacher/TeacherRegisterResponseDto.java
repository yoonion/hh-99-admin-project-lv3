package com.sparta.admin.dto.teacher;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.sparta.admin.entity.teacher.Teacher;
import lombok.Getter;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TeacherRegisterResponseDto {
    private final String name;
    private final int career;
    private final String company;
    private final String phoneNumber;
    private final String introduction;

    public TeacherRegisterResponseDto(Teacher teacher) {
        this.name = teacher.getName();
        this.career = teacher.getCareer();
        this.company = teacher.getCompany();
        this.phoneNumber = teacher.getPhoneNumber();
        this.introduction = teacher.getIntroduction();
    }
}
