package br.ufcg.animais.animais_ufcg.dtos.adoption_reports;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.ufcg.animais.animais_ufcg.models.animals.Animal;
import jakarta.validation.constraints.NotBlank;
import java.util.*;

public class AdoptionReportsPostPutRequestDTO {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("animal")
    @NotBlank(message = "An animal is required!")
    private Animal animal;

    @JsonProperty("animalOwnerName")
    @NotBlank(message = "Animal's owner name is required!")
    private String animalOwnerName;

    @JsonProperty("adoptionReport")
    @NotBlank(message = "Adoption report is required!")
    private String adoptionReport;

    @JsonProperty("photo")
    private byte[] photo;
}
