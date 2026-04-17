package com.fatecmc.muttley.student;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fatecmc.muttley.student.dto.StudentDTO;
import com.fatecmc.muttley.student.dto.StudentListDTO;
import com.fatecmc.muttley.user.User;
import com.fatecmc.muttley.user.dto.UserRole;

@Service
@Transactional
public class StudentService {

    private final StudentRepository repository;
    private final PasswordEncoder passwordEncoder;

    public StudentService(StudentRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public Student save(StudentDTO dto) {
        User user = new User();
        user.setEmail(dto.email());
        user.setPassword(passwordEncoder.encode(dto.ra()));
        user.setRole(UserRole.STUDENT);

        Student student = new Student(dto, user);
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

        student.setName(dto.name());
        student.setRa(dto.ra());
        student.setGithub(dto.github());
        student.setLinkedin(dto.linkedin());
        student.setCourse(dto.course());

        student.getUser().setEmail(dto.email());

        return repository.save(student);
    }

    public void delete(Long id) {
        Student student = findById(id);
        repository.delete(student);
    }

    public List<StudentListDTO> list(String search) {
        List<Student> students = repository
                .findByRaContainingIgnoreCase(search);

        return students.stream()
                .map(s -> new StudentListDTO(
                        s.getId(),
                        s.getName(),
                        s.getRa(),
                        s.getCourse()
                ))
                .toList();
    }
}