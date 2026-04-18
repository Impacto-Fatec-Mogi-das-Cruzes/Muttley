package com.fatecmc.muttley.teacher;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    List<Teacher> findByNameContainingIgnoreCaseOrUserEmailContainingIgnoreCaseOrCoursesNameContainingIgnoreCase(
        String name, String email, String courseName
    );
}
