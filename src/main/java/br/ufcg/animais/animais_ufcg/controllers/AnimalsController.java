package br.ufcg.animais.animais_ufcg.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class AnimalsController {
    
    @PostMapping()
    public ResponseEntity<?> creatingAnimal(@RequestBody @Valid AnimalPostPutRequestDTO animalPostPutRequestDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(AnimalService.creatingAnimal(animalPostPutRequestDto));
    }    
}
