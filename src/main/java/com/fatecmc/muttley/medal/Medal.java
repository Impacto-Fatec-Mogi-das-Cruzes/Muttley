package com.fatecmc.muttley.medal;

import java.util.List;

import com.fatecmc.muttley.competence.Competence;
import com.fatecmc.muttley.medal.dto.MedalCategory;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "medals")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MedalCategory category;

    @OneToMany(mappedBy = "medal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Competence> competences;
}