package com.adventure.base.controller;

import com.adventure.base.dto.HeroLastName;
import com.adventure.base.model.LastName;
import com.adventure.base.service.SurnameService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/game/admin/surname")
public class SurnameController {

    private final SurnameService surnameService;

    @Autowired
    public SurnameController(SurnameService surnameService) {
        this.surnameService = surnameService;
    }

    @GetMapping()
    public ResponseEntity<?> getAll() {

        List<LastName> surnames = surnameService.getAllSorted();

        if (surnames.size() == 0) {
            return ResponseEntity.ok("Фамилий не найдено");
        }

        return ResponseEntity.ok(surnames);
    }

    @GetMapping("/{id}")
    ResponseEntity<?> getOne(@PathVariable int id) {

        Optional<LastName> surname = surnameService.getOne(id);

        if (surname.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(surname);
    }

    @GetMapping("/random")
    ResponseEntity<String> getRandom() {
        return ResponseEntity.ok(surnameService.getRandomSurname());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable int id) {

        if (!surnameService.idExistence(id)) {
            return ResponseEntity.notFound().build();
        }

        surnameService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping()
    public ResponseEntity<?> create(@Valid @RequestBody HeroLastName surname,
                                    BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    bindingResult.getAllErrors().stream()
                            .map(DefaultMessageSourceResolvable::getDefaultMessage));
        }

        surnameService.addNew(surname.getLastName());

        return ResponseEntity.status(HttpStatus.CREATED).body(
                surnameService.getBySurname(surname.getLastName()).get());
    }
}
