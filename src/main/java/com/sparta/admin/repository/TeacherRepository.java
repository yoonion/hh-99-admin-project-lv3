package com.sparta.admin.repository;

import com.sparta.admin.entity.teacher.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}
