package com.sparta.admin.entity;

import com.sparta.admin.dto.lecture.LectureRegisterRequestDto;
import com.sparta.admin.dto.lecture.LectureUpdateRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import static com.sparta.admin.entity.LectureCategoryEnum.convertStringToCategory;

@Entity
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Getter
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lecture_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
    private String title;
    private int price;
    private String introduction;

    @Enumerated(value = EnumType.STRING)
    private LectureCategoryEnum category;

    @CreatedDate
    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    public Lecture(Teacher teacher, LectureRegisterRequestDto requestDto) {
        this.teacher = teacher;
        this.title = requestDto.getTitle();
        this.price = requestDto.getPrice();
        this.introduction = requestDto.getIntroduction();
        this.category = convertStringToCategory(requestDto.getCategory());
    }

    public void update(LectureUpdateRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.price = requestDto.getPrice();
        this.introduction = requestDto.getIntroduction();
        this.category = convertStringToCategory(requestDto.getCategory());
    }
}
