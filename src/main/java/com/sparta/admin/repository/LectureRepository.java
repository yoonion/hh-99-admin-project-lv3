package com.sparta.admin.repository;

import com.sparta.admin.entity.lecture.Lecture;
import com.sparta.admin.entity.lecture.LectureCategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
    List<Lecture> findAllByTeacherIdOrderByCreatedAtDesc(Long teacherId);

    List<Lecture> findAllByCategoryOrderByCreatedAtDesc(LectureCategoryEnum category);
}
