package com.fatecmc.muttley.student;

import com.fatecmc.muttley.student.dto.StudentDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Student {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String ra;

    private String github;
    private String linkedin;

    @Column(nullable = false)
    private String course;

    public Student(StudentDTO dto) {
        this.name = dto.name();
        this.ra = dto.ra();
        this.github = dto.github();
        this.linkedin = dto.linkedin();
        this.course = dto.course();
    }
}