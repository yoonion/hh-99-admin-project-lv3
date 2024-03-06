package com.sparta.admin.repository;

import com.sparta.admin.entity.Lecture;
import com.sparta.admin.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
    List<Lecture> findAllByTeacherIdOrderByCreatedAtDesc(Long teacherId);
}
