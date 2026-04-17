package com.fatecmc.muttley.course;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CourseService {

    private final CourseRepository repository;

    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    public List<Course> findAll() {
        return repository.findAll();
    }

    public Course findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Course not found"));
    }

    public void delete(Long id) {
        Course course = findById(id);
        repository.delete(course);
    }
}