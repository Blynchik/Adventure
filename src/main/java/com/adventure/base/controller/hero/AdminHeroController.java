package com.adventure.base.controller.hero;

import com.adventure.base.dto.hero.HeroDto;
import com.adventure.base.dto.hero.HeroDtoForCreating;
import com.adventure.base.model.AuthUser;
import com.adventure.base.model.Hero;
import com.adventure.base.service.HeroService;
import com.adventure.base.service.UserService;
import com.adventure.base.util.Converter;
import com.adventure.base.util.exception.ForbiddenActionException;
import com.adventure.base.util.exception.notFound.HeroNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/game/admin/hero")
public class AdminHeroController extends AbstractHeroController {

    @Autowired
    public AdminHeroController(HeroService heroService,
                               UserService userService) {
        super(heroService, userService);
    }

    @GetMapping()
    public ResponseEntity<?> getAll() {

        List<HeroDto> heroes = heroService.getAll()
                .stream()
                .map(Converter::getHeroDto)
                .toList();

        if (heroes.isEmpty()) {
            return ResponseEntity.ok("Героев не найдено");
        }

        return ResponseEntity.ok(heroes);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {

        if (!heroService.idExistence(id)) {
            throw new HeroNotFoundException("id " + id);
        }

        super.delete(id);
    }

    @PostMapping()
    public ResponseEntity<?> create(@Valid @RequestBody HeroDtoForCreating heroDto,
                                    BindingResult bindingResult,
                                    @RequestParam int userId) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    bindingResult.getAllErrors().stream()
                            .map(DefaultMessageSourceResolvable::getDefaultMessage));
        }

        return super.create(heroDto, userId);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void killHero(@PathVariable int id) {


        if (!heroService.idExistence(id)) {
            throw new HeroNotFoundException("id " + id);
        }


        super.killHero(id);
    }
}
