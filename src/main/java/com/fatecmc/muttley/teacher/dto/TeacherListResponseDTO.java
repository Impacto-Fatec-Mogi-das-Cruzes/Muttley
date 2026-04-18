package com.fatecmc.muttley.teacher.dto;

import java.util.List;

public record TeacherListResponseDTO(
    Long total,
    List<TeacherListDTO> teachers
) {}