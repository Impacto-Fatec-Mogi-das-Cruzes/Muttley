package com.fatecmc.muttley.student;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fatecmc.muttley.course.Course;
import com.fatecmc.muttley.course.CourseService;
import com.fatecmc.muttley.student.dto.StudentDTO;
import com.fatecmc.muttley.student.dto.StudentListDTO;
import com.fatecmc.muttley.student.dto.StudentListResponseDTO;
import com.fatecmc.muttley.user.User;
import com.fatecmc.muttley.user.dto.UserRole;

@Service
@Transactional
public class StudentService {

    private final StudentRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final CourseService courseService;

    public StudentService(StudentRepository repository, PasswordEncoder passwordEncoder, CourseService courseService) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.courseService = courseService;
    }

    public Student save(StudentDTO dto) {
        User user = new User();
        user.setEmail(dto.email());
        user.setPassword(passwordEncoder.encode(dto.ra()));
        user.setRole(UserRole.STUDENT);

        Course course = courseService.findById(dto.courseId());

        Student student = new Student(dto, user, course);
        return repository.save(student);
    }

    public List<Student> findAll() {
        return repository.findAll();
    }

    public Student findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    public Student update(Long id, StudentDTO dto) {
        Student student = findById(id);
        Course course = courseService.findById(dto.courseId());

        student.setName(dto.name());
        student.setRa(dto.ra());
        student.setGithub(dto.github());
        student.setLinkedin(dto.linkedin());
        student.setCourse(course);
        student.getUser().setEmail(dto.email());

        return repository.save(student);
    }

    public void delete(Long id) {
        Student student = findById(id);
        repository.delete(student);
    }

    public StudentListResponseDTO list(String search, Long courseId) {
        List<Student> students;

        if (search != null && courseId != null) {
            students = repository.findByRaContainingIgnoreCaseAndCourseId(search, courseId);
        } else if (search != null) {
            students = repository.findByRaContainingIgnoreCase(search);
        } else if (courseId != null) {
            students = repository.findByCourseId(courseId);
        } else {
            students = repository.findAll();
        }

        List<StudentListDTO> studentDTOs = students.stream()
            .map(s -> new StudentListDTO(
                    s.getId(),
                    s.getName(),
                    s.getRa(),
                    s.getCourse().getAbbreviation()
            ))
            .toList();

    return new StudentListResponseDTO(repository.count(), studentDTOs);
    }
}