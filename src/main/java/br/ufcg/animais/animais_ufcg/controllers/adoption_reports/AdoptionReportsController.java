package br.ufcg.animais.animais_ufcg.controllers.adoption_reports;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.ufcg.animais.animais_ufcg.models.*;

public class AdoptionReportsController {
    
    @PostMapping()
    public ResponseEntity<?> creatingAdoptionReport(@RequestBody @Valid adoptionReportsPostPutRequestDto adoptionReportsPostPutRequestDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(AdoptionReportService.creatingAdoptionReports(adoptionReportsPostPutRequestDto));
    }    
}
