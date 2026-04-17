package com.fatecmc.muttley.student;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByRaContainingIgnoreCase(String ra);
}
