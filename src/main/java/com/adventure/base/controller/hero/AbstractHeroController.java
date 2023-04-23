package com.adventure.base.controller.hero;

import com.adventure.base.dto.hero.HeroDto;
import com.adventure.base.dto.hero.HeroDtoForCreating;
import com.adventure.base.model.Hero;
import com.adventure.base.service.HeroService;
import com.adventure.base.service.UserService;
import com.adventure.base.util.Converter;
import com.adventure.base.util.exception.ForbiddenActionException;
import com.adventure.base.util.exception.notFound.HeroNotFoundException;
import com.adventure.base.util.exception.notFound.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class AbstractHeroController {

    protected HeroService heroService;
    protected UserService userService;

    public AbstractHeroController(HeroService heroService,
                                  UserService userService){
        this.heroService=heroService;
        this.userService=userService;
    }

    public ResponseEntity<HeroDto> getOne(int id) {

        Optional<Hero> hero = heroService.getOneById(id);

        if (hero.isEmpty()) {
            throw new HeroNotFoundException("id " + id);
        }

        return ResponseEntity.ok().body(
                Converter.getHeroDto(hero.get()));
    }

    public ResponseEntity<List<HeroDto>> getUserHeroes(int userId) {

        if (!userService.idExistence(userId)) {
            throw new UserNotFoundException("id " + userId);
        }

        return ResponseEntity.ok().body(
                heroService.getUserHeroesSortedByTime(
                                userId).stream()
                        .map(Converter::getHeroDto)
                        .collect(Collectors.toList()));
    }

    public ResponseEntity<?> create(HeroDtoForCreating heroDto,
                                    int userId) {

        if (!userService.idExistence(userId)) {
            throw new UserNotFoundException("id " + userId);
        }

        heroService.createNew(Converter.getHero(heroDto), userId);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                Converter.getHeroDto(
                        heroService.getUserHeroesSortedByTime(userId).get(0)));
    }

    public ResponseEntity<?> createWithRandomName(int userId){

        if (!userService.idExistence(userId)) {
            throw new UserNotFoundException("id " + userId);
        }

        heroService.createWithRandomName(userId);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                Converter.getHeroDto(
                        heroService.getUserHeroesSortedByTime(userId).get(0)));
    }

    public void delete(int id) {
        heroService.delete(id);
    }
}
