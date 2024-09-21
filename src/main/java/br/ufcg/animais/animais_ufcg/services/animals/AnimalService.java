package br.ufcg.animais.animais_ufcg.services.animals;

import br.ufcg.animais.animais_ufcg.dtos.animals.*;

import java.util.List;

public interface AnimalService {

    AnimalResponseDTO creatingAnimal(AnimalPostPutRequestDTO animalPostPutRequestDTO);

    void deleteAnimal(String id);
    AnimalResponseDTO getAnimalById(String id);
    AnimalResponseDTO updateAnimal(String id, AnimalPostPutRequestDTO animalPostPutRequestDTO);
    List<AnimalResponseDTO> getAvailableAnimals();
    List<AnimalResponseDTO> getAllAnimals();

}
