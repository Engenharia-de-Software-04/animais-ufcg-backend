package br.ufcg.animais.animais_ufcg.controllers.animals;

import br.ufcg.animais.animais_ufcg.models.animals.Animal;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import br.ufcg.animais.animais_ufcg.dtos.animals.*;
import br.ufcg.animais.animais_ufcg.services.animals.*;

@RestController
@RequestMapping("/animal")
public class AnimalsController {
    
    @Autowired
    AnimalService animalService;
    
    @PostMapping("/create")
    public ResponseEntity<?> creatingAnimal(@RequestBody @Valid AnimalPostPutRequestDTO animalPostPutRequestDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(animalService.creatingAnimal(animalPostPutRequestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAnimalById(@PathVariable String id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(animalService.getAnimalById(id));
    }
}
 