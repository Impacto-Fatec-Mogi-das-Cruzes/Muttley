package com.fatecmc.muttley.teacher.dto;

import java.util.List;

public record TeacherResponseDTO(
    Long id,
    String name,
    String email,
    List<String> courses,
    Boolean active
) {}