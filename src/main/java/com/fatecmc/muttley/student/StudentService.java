package com.fatecmc.muttley.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatecmc.muttley.student.dto.StudentDTO;
import com.fatecmc.muttley.student.dto.StudentListDTO;


@Service
public class StudentService {
    @Autowired 
    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public Student save(Student student) {
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
        student.setGithub(dto.github());
        student.setLinkedin(dto.linkedin());
        student.setCourse(dto.course());
        student.setRa(dto.ra());

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
