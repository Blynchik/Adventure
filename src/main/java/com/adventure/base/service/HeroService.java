package com.adventure.base.service;

import com.adventure.base.model.Hero;
import com.adventure.base.model.User;
import com.adventure.base.repository.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class HeroService {

    private final HeroRepository heroRepository;
    private final UserService userService;

    @Autowired
    public HeroService(HeroRepository heroRepository, UserService userService) {
        this.heroRepository = heroRepository;
        this.userService = userService;
    }

    @Transactional
    public void createNew(Hero hero, int userId) {
        hero.setUser(userService.getOneById(userId).get());
        heroRepository.save(hero);
    }

    public Optional<Hero> getOneById(int id) {
        return heroRepository.findById(id);
    }

    public List<Hero> getAll() {
        return heroRepository.findAll();
    }

    public List<Hero> getUserHeroes(int userId) {
        return userService.getOneById(
                        userId).get().getHeroes().stream()
                .sorted((h1, h2) -> h2.getCreatedAt().compareTo(h1.getCreatedAt()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(int id) {
        heroRepository.deleteById(id);
    }

    public boolean idExistence(int id){
        return heroRepository.existsById(id);
    }
}
