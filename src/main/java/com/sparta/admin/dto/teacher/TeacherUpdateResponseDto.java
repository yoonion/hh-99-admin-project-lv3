package com.sparta.admin.dto.teacher;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.sparta.admin.entity.Teacher;
import lombok.Getter;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TeacherUpdateResponseDto {
    private String name;
    private String career;
    private String company;
    private String phoneNumber;
    private String introduction;

    public TeacherUpdateResponseDto(Teacher teacher) {
        this.name = teacher.getName();
        this.career = teacher.getCareer();
        this.company = teacher.getCompany();
        this.phoneNumber = teacher.getPhoneNumber();
        this.introduction = teacher.getIntroduction();
    }
}
