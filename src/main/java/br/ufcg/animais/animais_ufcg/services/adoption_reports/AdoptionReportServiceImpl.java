package br.ufcg.animais.animais_ufcg.services.adoption_reports;

import org.springframework.beans.factory.annotation.Autowired;

import br.ufcg.animais.animais_ufcg.dtos.adoption_reports.*;
import br.ufcg.animais.animais_ufcg.models.adoption_reports.*;
import br.ufcg.animais.animais_ufcg.repositories.adoption_reports.*;

public class AdoptionReportServiceImpl {

    @Autowired
    AdoptionReportsRepository adoptionReportsRepository;

    @Autowired
    ModelMapper modelMapper;
    
    @Override
    public AdoptionReportsResponseDTO creatingAdoptionReports(AdoptionReportsPostPutRequestDTO adoptionReportPostPutRequestDTO) {

        AdoptionReports adoptionReport = modelMapper.map(adoptionReportPostPutRequestDTO, AdoptionReports.class);
        adoptionReportsRepository.save(adoptionReport);
        return modelMapper.map(adoptionReport, AdoptionReportsResponseDTO.class);
    }
}
