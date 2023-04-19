package com.adventure.base.controller.hero;

import com.adventure.base.dto.hero.HeroDto;
import com.adventure.base.model.Hero;
import com.adventure.base.service.HeroService;
import com.adventure.base.util.Converter;
import com.adventure.base.util.exception.notFound.HeroNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/game/user/hero")
public class UserHeroController {

    private final HeroService heroService;

    @Autowired
    public UserHeroController(HeroService heroService) {
        this.heroService = heroService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<HeroDto> getOne(@PathVariable int id) {

        Optional<Hero> hero = heroService.getOneById(id);

        if (hero.isEmpty()) {
            throw new HeroNotFoundException(String.valueOf(id));
        }

        return ResponseEntity.ok().body(
                Converter.getHeroDto(hero.get()));
    }
}
