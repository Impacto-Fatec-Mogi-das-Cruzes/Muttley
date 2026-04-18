package com.fatecmc.muttley.medal;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fatecmc.muttley.competence.Competence;
import com.fatecmc.muttley.medal.dto.MedalDTO;

@Service
@Transactional
public class MedalService {

    private final MedalRepository repository;

    public MedalService(MedalRepository repository) {
        this.repository = repository;
    }

    public Medal save(MedalDTO dto) {
        Medal medal = new Medal();
        medal.setName(dto.name());
        medal.setDescription(dto.description());
        medal.setCategory(dto.category());

        if (dto.competences() != null) {
            List<Competence> competences = dto.competences().stream()
                    .map(c -> {
                        Competence competence = new Competence();
                        competence.setName(c.name());
                        competence.setMedal(medal);
                        return competence;
                    })
                    .toList();
            medal.setCompetences(competences);
        }

        return repository.save(medal);
    }

    public List<Medal> findAll() {
        return repository.findAll();
    }

    public Medal findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medal not found"));
    }

    public Medal update(Long id, MedalDTO dto) {
        Medal medal = findById(id);

        medal.setName(dto.name());
        medal.setDescription(dto.description());
        medal.setCategory(dto.category());

        if (dto.competences() != null) {
            medal.getCompetences().clear();
            List<Competence> competences = dto.competences().stream()
                    .map(c -> {
                        Competence competence = new Competence();
                        competence.setName(c.name());
                        competence.setMedal(medal);
                        return competence;
                    })
                    .toList();
            medal.getCompetences().addAll(competences);
        }

        return repository.save(medal);
    }

    public void delete(Long id) {
        Medal medal = findById(id);
        repository.delete(medal);
    }
}