package br.ufcg.animais.animais_ufcg.services.animals;

import br.ufcg.animais.animais_ufcg.dtos.animals.*;

public interface AnimalService {

    AnimalResponseDTO creatingAnimal(AnimalPostPutRequestDTO animalPostPutRequestDTO);
    AnimalResponseDTO getAnimalById(String id);
    AnimalResponseDTO updateAnimal(String id, AnimalPostPutRequestDTO animalPostPutRequestDTO);
    
}
