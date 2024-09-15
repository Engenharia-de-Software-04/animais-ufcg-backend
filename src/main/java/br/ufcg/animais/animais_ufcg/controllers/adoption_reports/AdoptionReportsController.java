package br.ufcg.animais.animais_ufcg.controllers.adoption_reports;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import br.ufcg.animais.animais_ufcg.dtos.adoption_reports.*;
import br.ufcg.animais.animais_ufcg.services.adoption_reports.*;

@RestController
@RequestMapping("/adoption_reports")
public class AdoptionReportsController {
    
    @Autowired
    AdoptionReportService adoptionReportService;
    
    @PostMapping("/create")
    public ResponseEntity<?> creatingAdoptionReport(@RequestBody @Valid AdoptionReportsPostPutRequestDTO adoptionReportsPostPutRequestDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(adoptionReportService.creatingAdoptionReport(adoptionReportsPostPutRequestDTO));
    }    
}
