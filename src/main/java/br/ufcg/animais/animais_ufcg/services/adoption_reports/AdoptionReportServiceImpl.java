package br.ufcg.animais.animais_ufcg.services.adoption_reports;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufcg.animais.animais_ufcg.dtos.adoption_reports.*;
import br.ufcg.animais.animais_ufcg.exceptions.adoption_reports.AdoptionReportNotFound;
import br.ufcg.animais.animais_ufcg.exceptions.animals.*;
import br.ufcg.animais.animais_ufcg.models.adoption_reports.*;
import br.ufcg.animais.animais_ufcg.models.animals.Animal;
import br.ufcg.animais.animais_ufcg.models.enumerations.AnimalStatus;
import br.ufcg.animais.animais_ufcg.repositories.adoption_reports.*;
import br.ufcg.animais.animais_ufcg.repositories.animals.AnimalsRepository;

import org.springframework.stereotype.Service;

@Service
public class AdoptionReportServiceImpl implements AdoptionReportService {

    @Autowired
    AdoptionReportsRepository adoptionReportsRepository;

    @Autowired
    AnimalsRepository animalsRepository;

    @Autowired
    ModelMapper modelMapper;
    
    @Override
    public AdoptionReportsResponseDTO creatingAdoptionReport(AdoptionReportsPostPutRequestDTO adoptionReportPostPutRequestDTO) {
        
        Animal animal = animalsRepository.findById(adoptionReportPostPutRequestDTO.getAnimalID()).orElseThrow(AnimalNotFound::new);

        if(animal.getStatusAnimal() == AnimalStatus.AVAILABLE) {throw new AnimalNotAdopted();}
        
        AdoptionReport adoptionReport = modelMapper.map(adoptionReportPostPutRequestDTO, AdoptionReport.class);
        adoptionReportsRepository.save(adoptionReport);
        return modelMapper.map(adoptionReport, AdoptionReportsResponseDTO.class);
    }

    @Override
    public AdoptionReportsResponseDTO gettingAdoptionReport(String adoptionReportId) {
        AdoptionReport adoptionReport = adoptionReportsRepository.findById(adoptionReportId).orElseThrow(AdoptionReportNotFound::new);
        return modelMapper.map(adoptionReport, AdoptionReportsResponseDTO.class);
    }
}
