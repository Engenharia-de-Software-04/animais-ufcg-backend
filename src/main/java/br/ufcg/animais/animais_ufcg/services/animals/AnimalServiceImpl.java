package br.ufcg.animais.animais_ufcg.services.animals;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufcg.animais.animais_ufcg.dtos.animals.*;
import br.ufcg.animais.animais_ufcg.models.animals.*;
import br.ufcg.animais.animais_ufcg.repositories.animals.*;

@Service
public class AnimalServiceImpl implements AnimalService {

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

    public void deleteAnimal(String id){
        Animal animal = animalsRepository.findById(id).orElseThrow(AnimalNotExist::new);
        animalsRepository.delete(animal);
    }
}
