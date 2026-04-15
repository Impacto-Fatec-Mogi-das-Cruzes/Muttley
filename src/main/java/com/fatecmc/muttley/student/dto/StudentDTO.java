package com.fatecmc.muttley.student.dto;

import jakarta.validation.constraints.NotBlank;

public record StudentDTO(

    @NotBlank(message = "Name is required")
    String name,

    @NotBlank(message = "RA is required")
    String ra,

    String github,
    String linkedin,

    @NotBlank(message = "Course is required")
    String course

) {}