package br.ufcg.animais.animais_ufcg.services;

import br.ufcg.animais.animais_ufcg.dtos.*;

public interface AnimalService {
    
    AnimalResponseDTO creatingAnimal(AnimalPostPutRequestDTO animalPostPutRequestDTO);
}
