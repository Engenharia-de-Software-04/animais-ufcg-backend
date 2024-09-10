package br.ufcg.animais.animais_ufcg.services.animals;

import br.ufcg.animais.animais_ufcg.dtos.animals.*;
import br.ufcg.animais.animais_ufcg.models.animals.Animal;


public interface AnimalService {

    AnimalResponseDTO creatingAnimal(AnimalPostPutRequestDTO animalPostPutRequestDTO);
    Animal getAnimalById(String id);
    
}
