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

    // 연관 관계의 주인은 Lecture 이다.
    // 데이터 베이스 테이블의 다대일, 일대다 관계에서는 '다(Many)' 쪽이 외래 키를 갖기 때문에 @ManyToOne가 항상 연관관계의 주인이된다.
    // 따라서 mappedBy 속성 또한 없다.
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
