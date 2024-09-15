package br.ufcg.animais.animais_ufcg.services.animals;

import br.ufcg.animais.animais_ufcg.models.enumerations.AnimalStatus;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufcg.animais.animais_ufcg.dtos.animals.*;
import br.ufcg.animais.animais_ufcg.models.animals.*;
import br.ufcg.animais.animais_ufcg.repositories.animals.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<AnimalResponseDTO> getAvailableAnimals() {
        List<Animal> animals = animalsRepository.findByStatusAnimal(AnimalStatus.AVAILABLE);
        if(animals.isEmpty()){
            throw new RuntimeException("Nenhum animal disponível encontrado");
        }
        return animals.stream()
                .map(animal->modelMapper.map(animal, AnimalResponseDTO.class))
                .collect(Collectors.toList());
    }
}
