package br.ufcg.animais.animais_ufcg.services.animals;

import br.ufcg.animais.animais_ufcg.dto.animals.AnimalPostPutRequestDTO;
import br.ufcg.animais.animais_ufcg.dto.animals.AnimalResponseDTO;

public interface AnimalService {

    AnimalResponseDTO creatingAnimal(AnimalPostPutRequestDTO animalPostPutRequestDTO);
    
}
