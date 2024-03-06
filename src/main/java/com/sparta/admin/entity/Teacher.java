package com.sparta.admin.entity;

import com.sparta.admin.dto.teacher.TeacherRegisterRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    private Long id;
    private String name;
    private String career;
    private String company;
    private String phoneNumber;
    private String introduction;

    public Teacher(TeacherRegisterRequestDto requestDto) {
        this.name = requestDto.getName();
        this.career = requestDto.getCareer();
        this.company = requestDto.getCompany();
        this.phoneNumber = requestDto.getPhoneNumber();
        this.introduction = requestDto.getIntroduction();
    }
}
