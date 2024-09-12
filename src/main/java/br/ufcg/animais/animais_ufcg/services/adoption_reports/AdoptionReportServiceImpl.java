package br.ufcg.animais.animais_ufcg.services.adoption_reports;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufcg.animais.animais_ufcg.dtos.adoption_reports.*;
import br.ufcg.animais.animais_ufcg.exceptions.adoption_reports.AdoptionReportNotFound;
import br.ufcg.animais.animais_ufcg.models.adoption_reports.*;
import br.ufcg.animais.animais_ufcg.repositories.adoption_reports.*;

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

    @Override
    public AdoptionReportsResponseDTO gettingAdoptionReport(UUID adoptionReportId) {
        AdoptionReport adoptionReport = adoptionReportsRepository.findById(adoptionReportId).orElse(null);
        if (adoptionReport == null) {
            throw new AdoptionReportNotFound();
        }
        return modelMapper.map(adoptionReport, AdoptionReportsResponseDTO.class);
    }
}
