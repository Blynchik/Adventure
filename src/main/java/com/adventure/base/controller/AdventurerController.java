package com.adventure.base.controller;

import com.adventure.base.dto.AdventurerDto;
import com.adventure.base.model.Adventurer;
import com.adventure.base.service.AdventurerService;
import com.adventure.base.service.util.AdventurerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game/users/adventurers")
public class AdventurerController {

    private final AdventurerService adventurerService;

    @Autowired
    public AdventurerController(AdventurerService adventurerService){
        this.adventurerService = adventurerService;
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> create(@RequestBody Adventurer adventurer){
        adventurerService.create(adventurer);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdventurerDto> getAdventurer(@PathVariable long id) {
        return ResponseEntity.ok().body(AdventurerUtil.getDto(adventurerService.getAdventurerById(id).orElse(null)));
    }

    @GetMapping()
    public ResponseEntity<List<Adventurer>> getAllAdventurers(){
        return ResponseEntity.ok().body(adventurerService.getAllAdventurers());
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateAdventurer(@PathVariable long id,
                                                       @RequestBody Adventurer adventurer){

        adventurerService.update(id, adventurer);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable long id){
        adventurerService.delete(id);
        return ResponseEntity.ok().build();
    }
}
