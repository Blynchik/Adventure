package com.adventure.base.service;

import com.adventure.base.model.Hero;
import com.adventure.base.repository.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class HeroService {

    private final HeroRepository heroRepository;

    @Autowired
    public HeroService(HeroRepository heroRepository){
        this.heroRepository = heroRepository;
    }

    @Transactional
    public void createNew(Hero hero){
        heroRepository.save(hero);
    }

    public Optional<Hero> getOneById(int id){
        return heroRepository.findById(id);
    }

    public List<Hero> getAll(){
        return heroRepository.findAll();
    }

    @Transactional
    public void delete(int id){
        heroRepository.deleteById(id);
    }
}
