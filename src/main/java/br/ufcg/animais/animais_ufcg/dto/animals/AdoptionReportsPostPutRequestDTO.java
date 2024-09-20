package br.ufcg.animais.animais_ufcg.dto.animals;
import br.ufcg.animais.animais_ufcg.models.animals.Animal;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdoptionReportsPostPutRequestDTO {

    @JsonProperty("id")
    private String id;

    @JsonProperty("animalID")
    @NotBlank(message = "An animal's ID is required!")
    private String animalID;

    @JsonProperty("animalOwnerName")
    @NotBlank(message = "Animal's owner name is required!")
    private String animalOwnerName;

    @JsonProperty("adoptionReport")
    @NotBlank(message = "Adoption report is required!")
    private String adoptionReport;

    @JsonProperty("photo")
    private byte[] photo;
}
