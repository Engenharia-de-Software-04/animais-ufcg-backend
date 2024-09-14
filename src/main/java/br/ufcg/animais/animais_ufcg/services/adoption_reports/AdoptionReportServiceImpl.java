package br.ufcg.animais.animais_ufcg.services.adoption_reports;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufcg.animais.animais_ufcg.dtos.adoption_reports.*;
import br.ufcg.animais.animais_ufcg.models.adoption_reports.*;
import br.ufcg.animais.animais_ufcg.repositories.adoption_reports.*;
import org.springframework.stereotype.Service;

@Service
public class AdoptionReportServiceImpl implements AdoptionReportService {

    @Autowired
    AdoptionReportsRepository adoptionReportsRepository;

    @Autowired
    ModelMapper modelMapper;
    
    @Override
    public AdoptionReportsResponseDTO creatingAdoptionReport(AdoptionReportsPostPutRequestDTO adoptionReportPostPutRequestDTO) {

        AdoptionReport adoptionReport = modelMapper.map(adoptionReportPostPutRequestDTO, AdoptionReport.class);
        adoptionReportsRepository.save(adoptionReport);
        return modelMapper.map(adoptionReport, AdoptionReportsResponseDTO.class);
    }
}
