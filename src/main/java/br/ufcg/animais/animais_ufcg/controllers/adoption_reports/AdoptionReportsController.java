package br.ufcg.animais.animais_ufcg.controllers.adoption_reports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.ufcg.animais.animais_ufcg.models.*;
import br.ufcg.animais.animais_ufcg.services.adoption_reports.AdoptionReportService;

@RequestMapping("/adoption_reports")
public class AdoptionReportsController {

    @Autowired
    AdoptionReportService adoptionReportService;
    
    @PostMapping
    public ResponseEntity<?> creatingAdoptionReport(@RequestBody @Valid adoptionReportsPostPutRequestDto adoptionReportsPostPutRequestDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(adoptionReportService.creatingAdoptionReports(adoptionReportsPostPutRequestDto));
    }    
}
