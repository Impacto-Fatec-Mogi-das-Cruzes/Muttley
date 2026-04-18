package com.fatecmc.muttley.teacher;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fatecmc.muttley.course.Course;
import com.fatecmc.muttley.course.CourseService;
import com.fatecmc.muttley.teacher.dto.TeacherDTO;
import com.fatecmc.muttley.user.User;
import com.fatecmc.muttley.user.dto.UserRole;

@Service
@Transactional
public class TeacherService {

    private final TeacherRepository repository;
    private final CourseService courseService;
    private final PasswordEncoder passwordEncoder;

    public TeacherService(TeacherRepository repository,
                          CourseService courseService,
                          PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.courseService = courseService;
        this.passwordEncoder = passwordEncoder;
    }

    public Teacher save(TeacherDTO dto) {
        User user = new User();
        user.setEmail(dto.email());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setRole(UserRole.TEACHER);

        List<Course> courses = dto.courseIds().stream()
                .map(courseService::findById)
                .toList();

        Teacher teacher = new Teacher();
        teacher.setName(dto.name());
        teacher.setUser(user);
        teacher.setCourses(courses);
        teacher.setActive(true);

        return repository.save(teacher);
    }

    public Teacher findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
    }

    public Teacher update(Long id, TeacherDTO dto) {
        Teacher teacher = findById(id);

        List<Course> courses = dto.courseIds().stream()
                .map(courseService::findById)
                .toList();

        teacher.setName(dto.name());
        teacher.setCourses(courses);
        teacher.getUser().setEmail(dto.email());
        teacher.getUser().setPassword(passwordEncoder.encode(dto.password()));

        if (dto.active() != null) {
            teacher.setActive(dto.active());
        }

        return repository.save(teacher);
    }

    public void delete(Long id) {
        Teacher teacher = findById(id);
        repository.delete(teacher);
    }

}