package com.sparta.admin.entity;

import com.sparta.admin.dto.teacher.TeacherRegisterRequestDto;
import com.sparta.admin.dto.teacher.TeacherUpdateRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int career;

    @Column(nullable = false)
    private String company;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String introduction;

    // Teacher 는 연관 관계의 주인이 아니다.
    // 연관관계의 주인은 외래키를 관리하는 Lecture 가 되며, Teacher 은 mappedBy를 이용해 연관관계의 주인을 지정한다.
    // mappedBy의 값은 주인 엔티티의 실제 참조 필드 변수명이다.
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lecture> lectures = new ArrayList<>(); // 초기화를 해주며 NPE 를 피할 수 있다.

    public Teacher(TeacherRegisterRequestDto requestDto) {
        this.name = requestDto.getName();
        this.career = requestDto.getCareer();
        this.company = requestDto.getCompany();
        this.phoneNumber = requestDto.getPhoneNumber();
        this.introduction = requestDto.getIntroduction();
    }

    // 강사 정보 수정
    public void update(TeacherUpdateRequestDto requestDto) {
        this.name = requestDto.getName();
        this.career = requestDto.getCareer();
        this.company = requestDto.getCompany();
        this.phoneNumber = requestDto.getPhoneNumber();
        this.introduction = requestDto.getIntroduction();
    }
}
