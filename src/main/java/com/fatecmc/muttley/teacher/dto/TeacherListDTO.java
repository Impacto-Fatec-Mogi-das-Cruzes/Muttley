package com.fatecmc.muttley.teacher.dto;

import java.util.List;

public record TeacherListDTO(
    Long id,
    String name,
    String email,
    List<String> courses,
    boolean active
) {}