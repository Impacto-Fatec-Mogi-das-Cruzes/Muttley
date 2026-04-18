package com.fatecmc.muttley.medal.dto;

import java.util.List;

import com.fatecmc.muttley.competence.dto.CompetenceDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MedalDTO(

    @NotBlank(message = "Name is required")
    String name,

    @NotBlank(message = "Description is required")
    String description,

    @NotNull(message = "Category is required")
    MedalCategory category,

    List<CompetenceDTO> competences

) {}