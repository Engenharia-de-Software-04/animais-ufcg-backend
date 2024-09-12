package br.ufcg.animais.animais_ufcg.services.adoption_reports;

import java.util.UUID;

import br.ufcg.animais.animais_ufcg.dtos.adoption_reports.*;

public interface AdoptionReportService {
    
    AdoptionReportsResponseDTO creatingAdoptionReport(AdoptionReportsPostPutRequestDTO adoptionReportsPostPutRequestDTO);
    
    AdoptionReportsResponseDTO gettingAdoptionReport(UUID adoptionReportId);
}
