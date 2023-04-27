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

    @Autowired
    public AbstractHeroController(HeroService heroService,
                                  UserService userService) {
        this.heroService = heroService;
        this.userService = userService;
    }

    public ResponseEntity<HeroDto> getOne(int id) {

        Optional<Hero> hero = heroService.getOneById(id);

        checkHeroExistence(hero, id);

        return ResponseEntity.ok().body(
                Converter.getHeroDto(hero.get()));
    }

    public ResponseEntity<List<HeroDto>> getUserHeroes(int userId) {

        checkUserExistence(userId);

        return ResponseEntity.ok().body(
                heroService.getUserHeroesSortedByTime(
                                userId).stream()
                        .map(Converter::getHeroDto)
                        .collect(Collectors.toList()));
    }

    public ResponseEntity<?> create(HeroDtoForCreating heroDto,
                                    int userId) {

        checkUserExistence(userId);

        List<Hero> heroes = heroService.getUserHeroesSortedByTime(userId);

        if (!heroes.isEmpty() && heroes.get(0).isEnable()) {
            throw new ForbiddenActionException("Нельзя создавать нового героя, пока жив старый");
        }

        Hero hero = Converter.getHero(heroDto);

        heroService.createNew(hero, userId);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                Converter.getHeroDto(hero));
    }

    public ResponseEntity<?> createWithRandomName(int userId) {

        checkUserExistence(userId);

        List<Hero> heroes = heroService.getUserHeroesSortedByTime(userId);

        if (!heroes.isEmpty() && heroes.get(0).isEnable()) {
            throw new ForbiddenActionException("Нельзя создавать нового героя, пока жив старый");
        }

        Hero hero = heroService.createWithRandomName(userId);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                Converter.getHeroDto(hero));
    }

    public void delete(int id) {
        heroService.delete(id);
    }

    public void killHero(int heroId){
        heroService.killHero(heroId);
    }

    protected void checkUserExistence(int id) {

        if (!userService.idExistence(id)) {
            throw new UserNotFoundException("id " + id);
        }
    }

    protected void checkHeroExistence(Optional<Hero> hero, int id) {

        if (hero.isEmpty()) {
            throw new HeroNotFoundException("id " + id);
        }
    }
}
