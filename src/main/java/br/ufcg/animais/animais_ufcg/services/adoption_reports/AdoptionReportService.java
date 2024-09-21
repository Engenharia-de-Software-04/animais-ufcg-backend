package br.ufcg.animais.animais_ufcg.services.adoption_reports;


import br.ufcg.animais.animais_ufcg.dtos.adoption_reports.AdoptionReportsPostPutRequestDTO;
import br.ufcg.animais.animais_ufcg.dtos.adoption_reports.AdoptionReportsResponseDTO;
import java.util.List;

public interface AdoptionReportService {
    
    AdoptionReportsResponseDTO creatingAdoptionReport(AdoptionReportsPostPutRequestDTO adoptionReportsPostPutRequestDTO);

    List<AdoptionReportsResponseDTO> gettingAllReports();
}
