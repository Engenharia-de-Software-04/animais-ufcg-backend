package br.ufcg.animais.animais_ufcg.services;

import org.springframework.beans.factory.annotation.Autowired;

import br.ufcg.animais.animais_ufcg.dtos.*;
import br.ufcg.animais.animais_ufcg.models.*;
import br.ufcg.animais.animais_ufcg.repositories.*;

public class AnimalServiceImpl {

    @Autowired
    AnimalsRepository animalsRepository;

    @Autowired
    ModelMapper modelMapper;
    
    @Override
    public AnimalResponseDTO creatingAnimal(AnimalPostPutRequestDTO animalPostPutRequestDTO) {

        Animal animal = modelMapper.map(animalPostPutRequestDTO, Animal.class);
        animalsRepository.save(animal);
        return modelMapper.map(animal, AnimalResponseDTO.class);
    }
}
