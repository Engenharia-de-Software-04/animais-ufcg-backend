package br.ufcg.animais.animais_ufcg.services.animals;

import br.ufcg.animais.animais_ufcg.exceptions.animals.AnimalNotFoundException;
import br.ufcg.animais.animais_ufcg.exceptions.animals.AnimalNotFound;
import br.ufcg.animais.animais_ufcg.exceptions.animals.AnimalAvailableNotFoundException;
import br.ufcg.animais.animais_ufcg.models.enumerations.AnimalStatus;
import br.ufcg.animais.animais_ufcg.dtos.animals.AnimalPostPutRequestDTO;
import br.ufcg.animais.animais_ufcg.dtos.animals.AnimalResponseDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.ufcg.animais.animais_ufcg.dtos.animals.*;
import br.ufcg.animais.animais_ufcg.models.animals.*;
import br.ufcg.animais.animais_ufcg.repositories.animals.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    public void deleteAnimal(String id){
        if (animalsRepository.findById(id).isEmpty()){
            throw new AnimalNotFoundException();
        }
        animalsRepository.deleteById(id);
    }

    @Override
    public AnimalResponseDTO getAnimalById(String id) {
        Animal animal =  animalsRepository.findById(id).orElseThrow(AnimalNotFound::new);
        return new AnimalResponseDTO(animal);
    }

    @Override
    public AnimalResponseDTO updateAnimal(String id, AnimalPostPutRequestDTO animalPostPutRequestDTO) {
        Animal animal = animalsRepository.findById(id).orElseThrow(AnimalNotFound::new);
        modelMapper.map(animalPostPutRequestDTO, animal);
        animal.setId(id);
        animalsRepository.save(animal);
        return modelMapper.map(animal, AnimalResponseDTO.class);
    }

    @Override
    public List<AnimalResponseDTO> getAvailableAnimals() {
        List<Animal> animals = animalsRepository.findByStatusAnimal(AnimalStatus.AVAILABLE);
        if(animals.isEmpty()){
            throw new AnimalAvailableNotFoundException();
        }

        List<AnimalResponseDTO> animalList = new ArrayList<>();
        for (Animal animal : animals) {
            AnimalResponseDTO dto = new AnimalResponseDTO();
            dto.setId(animal.getId());
            dto.setStatusAnimal(animal.getStatusAnimal());
            dto.setAnimalSex(animal.getAnimalSex());
            dto.setAnimalName(animal.getAnimalName());
            dto.setAnimalAge(animal.getAnimalAge());
            dto.setAnimalSpecie(animal.getAnimalSpecie());
            dto.setAnimalDescription(animal.getAnimalDescription());
            dto.setAnimalIsCastrated(animal.getAnimalIsCastrated());
            dto.setAnimalIsVaccinated(animal.getAnimalIsVaccinated());
            dto.setPhoto(animal.getPhoto());

            animalList.add(dto);
        }

        return animalList;
    }

    @Override
    public List<AnimalResponseDTO> getAllAnimals() {
        List<Animal> animals = animalsRepository.findAll();
        if(animals.isEmpty()){
            throw new AnimalNotFoundException();
        }

        List<AnimalResponseDTO> animalList = new ArrayList<>();
        for (Animal animal : animals) {
            AnimalResponseDTO dto = new AnimalResponseDTO();
            dto.setId(animal.getId());
            dto.setStatusAnimal(animal.getStatusAnimal());
            dto.setAnimalSex(animal.getAnimalSex());
            dto.setAnimalName(animal.getAnimalName());
            dto.setAnimalAge(animal.getAnimalAge());
            dto.setAnimalSpecie(animal.getAnimalSpecie());
            dto.setAnimalDescription(animal.getAnimalDescription());
            dto.setAnimalIsCastrated(animal.getAnimalIsCastrated());
            dto.setAnimalIsVaccinated(animal.getAnimalIsVaccinated());
            dto.setPhoto(animal.getPhoto());

            animalList.add(dto);
        }

        return animalList;
    }
}
