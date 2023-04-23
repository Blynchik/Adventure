package com.adventure.base.controller.hero;

import com.adventure.base.dto.hero.HeroDto;
import com.adventure.base.dto.hero.HeroDtoForCreating;
import com.adventure.base.model.AuthUser;
import com.adventure.base.model.Hero;
import com.adventure.base.service.HeroService;
import com.adventure.base.service.UserService;
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
@RequestMapping("/game/user/hero")
public class UserHeroController extends AbstractHeroController {

    @Autowired
    public UserHeroController(HeroService heroService, UserService userService) {
        super(heroService, userService);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HeroDto> getOne(@PathVariable int id) {
        return super.getOne(id);
    }

    @GetMapping()
    public ResponseEntity<List<HeroDto>> getOwn(@AuthenticationPrincipal AuthUser authUser) {
        return super.getUserHeroes(authUser.id());
    }

    @PostMapping()
    public ResponseEntity<?> createOwn(@AuthenticationPrincipal AuthUser authUser,
                                       @Valid @RequestBody HeroDtoForCreating heroDto,
                                       BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    bindingResult.getAllErrors().stream()
                            .map(DefaultMessageSourceResolvable::getDefaultMessage));
        }

        return super.create(heroDto, authUser.id());
    }

    @PostMapping("/createRandom")
    public ResponseEntity<?> createOwnRandom(@AuthenticationPrincipal AuthUser authUser) {
        return super.createWithRandomName(authUser.id());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOwn(@AuthenticationPrincipal AuthUser authUser,
                          @PathVariable int id) {

        Optional<Hero> hero = heroService.getOneById(id);

        if (hero.isEmpty()) {
            throw new HeroNotFoundException("id " + id);
        }

        if (hero.get().getUser().getId() != authUser.id()) {
            throw new ForbiddenActionException("Нельзя удалить чужого героя");
        }

        super.delete(id);
    }
}
