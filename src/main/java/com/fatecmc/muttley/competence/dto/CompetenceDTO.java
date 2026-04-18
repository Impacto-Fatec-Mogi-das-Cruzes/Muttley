package com.fatecmc.muttley.competence.dto;

import jakarta.validation.constraints.NotBlank;

public record CompetenceDTO(
    @NotBlank(message = "Competence name is required")
    String name
) {}