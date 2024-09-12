package br.ufcg.animais.animais_ufcg.dtos.adoption_reports;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.ufcg.animais.animais_ufcg.models.adoption_reports.AdoptionReport;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdoptionReportsResponseDTO {

    @JsonProperty("id")
    private UUID id;

    // @JsonProperty("animal")
    // @NotBlank(message = "An animal is required!")
    // private Animal animal;

    @JsonProperty("animalOwnerName")
    @NotBlank(message = "Animal's owner name is required!")
    private String animalOwnerName;

    @JsonProperty("adoptionReport")
    @NotBlank(message = "Adoption report is required!")
    private String adoptionReport;

    @JsonProperty("photo")
    private byte[] photo;

    public AdoptionReportsResponseDTO(AdoptionReport adoptionReport) {
        this.id = adoptionReport.getId();
        // this.animal = adoptionReport.getAnimal();
        this.animalOwnerName = adoptionReport.getAnimalOwnerName();
        this.adoptionReport = adoptionReport.getAdoptionReport();
        this.photo = adoptionReport.getPhoto();
    }
}