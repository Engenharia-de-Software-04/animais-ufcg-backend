package br.ufcg.animais.animais_ufcg.services.adoption_reports;

import br.ufcg.animais.animais_ufcg.dto.animals.AdoptionReportsPostPutRequestDTO;
import br.ufcg.animais.animais_ufcg.dto.animals.AdoptionReportsResponseDTO;
import br.ufcg.animais.animais_ufcg.exceptions.adoption_reports.ReportNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufcg.animais.animais_ufcg.models.adoption_reports.*;
import br.ufcg.animais.animais_ufcg.repositories.adoption_reports.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<AdoptionReportsResponseDTO> gettingAllReports() {
        List<AdoptionReport> reports = adoptionReportsRepository.findAll();
        if(reports.isEmpty()){
            throw new ReportNotFoundException();
        }
        return reports.stream()
                .map(report -> modelMapper.map(report, AdoptionReportsResponseDTO.class))
                .collect(Collectors.toList());
    }
}
