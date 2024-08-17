package br.ufcg.animais.animais_ufcg.services.adoption_reports;

import br.ufcg.animais.animais_ufcg.dtos.adoption_reports.*;

public interface AdoptionReportService {
    
    AdoptionReportResponseDTO creatingAdoptionReport(AdoptionReportsPostPutRequestDTO adoptionReportsPostPutRequestDTO);
}
