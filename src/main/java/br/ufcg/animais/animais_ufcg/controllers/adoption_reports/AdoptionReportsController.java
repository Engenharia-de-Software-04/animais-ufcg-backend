package br.ufcg.animais.animais_ufcg.controllers.adoption_reports;

import br.ufcg.animais.animais_ufcg.dtos.adoption_reports.AdoptionReportsPostPutRequestDTO;
import br.ufcg.animais.animais_ufcg.dtos.animals.AnimalPostPutRequestDTO;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import br.ufcg.animais.animais_ufcg.services.adoption_reports.*;

@RestController
@RequestMapping("/adoption_report")
public class AdoptionReportsController {

    @Autowired
    AdoptionReportService adoptionReportService;

    @PostMapping("/create")
    public ResponseEntity<?> creatingAdoptionReport(@RequestBody @Valid AdoptionReportsPostPutRequestDTO adoptionReportsPostPutRequestDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(adoptionReportService.creatingAdoptionReport(adoptionReportsPostPutRequestDTO));
    }
    
    @GetMapping("/get/{id}")    
    public ResponseEntity<?> gettingAdoptionReport(@PathVariable String id) {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(adoptionReportService.gettingAdoptionReport(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> gettingAllReports(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(adoptionReportService.gettingAllReports());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAdoptionReport(@PathVariable String id){
        adoptionReportService.deleteAdoptionReport(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAdoptionReport(@PathVariable String id, @RequestBody @Valid AdoptionReportsPostPutRequestDTO adoptionReportsPostPutRequestDTO) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(adoptionReportService.updateAdoptionReport(id, adoptionReportsPostPutRequestDTO));
    }
}
