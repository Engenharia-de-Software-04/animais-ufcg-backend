package br.ufcg.animais.animais_ufcg.services.adoption_reports;


import br.ufcg.animais.animais_ufcg.dtos.adoption_reports.AdoptionReportsPostPutRequestDTO;
import br.ufcg.animais.animais_ufcg.dtos.adoption_reports.AdoptionReportsResponseDTO;
import br.ufcg.animais.animais_ufcg.dtos.animals.AnimalPostPutRequestDTO;
import br.ufcg.animais.animais_ufcg.dtos.animals.AnimalResponseDTO;

import java.util.List;

public interface AdoptionReportService {
    
    AdoptionReportsResponseDTO creatingAdoptionReport(AdoptionReportsPostPutRequestDTO adoptionReportsPostPutRequestDTO);
    
    AdoptionReportsResponseDTO gettingAdoptionReport(String adoptionReportId);

    List<AdoptionReportsResponseDTO> gettingAllReports();

    AdoptionReportsResponseDTO updateAdoptionReport(String id, AdoptionReportsPostPutRequestDTO adoptionReportsPostPutRequestDTO);
}
