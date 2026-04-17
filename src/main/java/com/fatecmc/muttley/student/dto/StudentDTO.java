package com.fatecmc.muttley.student.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record StudentDTO(

    @NotBlank(message = "Name is required")
    String name,

    @NotBlank(message = "RA is required")
    String ra,

    String github,
    String linkedin,

    @NotNull(message = "Course is required")
    Long courseId,

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email")
    String email

) {}