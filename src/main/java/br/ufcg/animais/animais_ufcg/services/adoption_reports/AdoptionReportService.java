package br.ufcg.animais.animais_ufcg.services.adoption_reports;

import br.ufcg.animais.animais_ufcg.dto.adoption_reports.*;
import br.ufcg.animais.animais_ufcg.dto.animals.AdoptionReportsPostPutRequestDTO;
import br.ufcg.animais.animais_ufcg.dto.animals.AdoptionReportsResponseDTO;

import java.util.List;

public interface AdoptionReportService {
    
    AdoptionReportsResponseDTO creatingAdoptionReport(AdoptionReportsPostPutRequestDTO adoptionReportsPostPutRequestDTO);

    List<AdoptionReportsResponseDTO> gettingAllReports();
}
