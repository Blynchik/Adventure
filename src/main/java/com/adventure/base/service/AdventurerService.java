package com.adventure.base.service;

import com.adventure.base.model.Adventurer;
import com.adventure.base.repository.AdventurerRepository;
import com.adventure.base.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class AdventurerService {

    private final AdventurerRepository adventurerRepository;

    @Autowired
    public AdventurerService(AdventurerRepository adventurerRepository) {
        this.adventurerRepository = adventurerRepository;
    }

    @Transactional
    public void create(Adventurer adventurer) {
        adventurerRepository.save(adventurer);
    }

    public Optional<Adventurer> getAdventurerById(long id) {
        return adventurerRepository.findById(id);
    }

    public List<Adventurer> getAllAdventurers() {
        return adventurerRepository.findAll();
    }

    @Transactional
    public void update(long id, Adventurer updatedAdventurer) {

        if (adventurerRepository.existsById(id)) {
            updatedAdventurer.setId(id);
            adventurerRepository.save(updatedAdventurer);
        }
    }

    @Transactional
    public void delete(long id) {

        if (adventurerRepository.existsById(id)) {
            adventurerRepository.deleteById(id);
        }
    }
}
