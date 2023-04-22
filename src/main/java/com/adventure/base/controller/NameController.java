package com.adventure.base.controller;

import com.adventure.base.dto.HeroFirstName;
import com.adventure.base.model.FirstName;
import com.adventure.base.service.NameService;
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
@RequestMapping("/game/admin/name")
public class NameController {

    private final NameService nameService;

    @Autowired
    public NameController(NameService nameService) {
        this.nameService = nameService;
    }

    @GetMapping()
    public ResponseEntity<?> getAll() {

        List<FirstName> names = nameService.getAllSorted();

        if (names.size() == 0) {
            return ResponseEntity.ok("Имен не найдено");
        }

        return ResponseEntity.ok(names);
    }

    @GetMapping("/{id}")
    ResponseEntity<?> getOne(@PathVariable int id){

        Optional<FirstName> name = nameService.getOne(id);

        if(name.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(name);
    }

    @GetMapping("/random")
    ResponseEntity<String> getRandom(){
       return ResponseEntity.ok(nameService.getRandomName());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable int id){

        if(!nameService.idExistence(id)){
            return ResponseEntity.notFound().build();
        }

        nameService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping()
    public ResponseEntity<?> create (@Valid @RequestBody HeroFirstName name,
                                     BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    bindingResult.getAllErrors().stream()
                            .map(DefaultMessageSourceResolvable::getDefaultMessage));
        }

        nameService.addNew(name.getFirstName());

        return ResponseEntity.status(HttpStatus.CREATED).body(
                nameService.getByName(name.getFirstName()).get());
    }
}
