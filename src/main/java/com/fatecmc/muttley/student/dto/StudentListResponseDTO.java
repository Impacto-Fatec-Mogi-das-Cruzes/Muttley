package com.fatecmc.muttley.student.dto;

import java.util.List;

public record StudentListResponseDTO(
    Long total,
    List<StudentListDTO> students
) {}
