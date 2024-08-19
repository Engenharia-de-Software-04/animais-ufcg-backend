package br.ufcg.animais.animais_ufcg.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/animals")
public class AnimalsController {
    
    @Autowired
    AnimalService animalService;
    
    @PostMapping
    public ResponseEntity<?> creatingAnimal(@RequestBody @Valid AnimalsPostPutRequestDTO animalPostPutRequestDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(animalService.creatingAnimal(animalPostPutRequestDto));
    }      
}
