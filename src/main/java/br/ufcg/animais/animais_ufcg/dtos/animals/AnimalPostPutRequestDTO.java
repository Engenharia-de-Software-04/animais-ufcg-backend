package br.ufcg.animais.animais_ufcg.dtos.animals;

import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonProperty;
import br.ufcg.animais.animais_ufcg.models.enumerations.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnimalPostPutRequestDTO {

    @JsonProperty("id")
    private String id;

    @JsonProperty("statusAnimal")
    @Builder.Default
    private AnimalStatus statusAnimal = AnimalStatus.AVAILABLE;

    @JsonProperty("animalSex")
    @NotNull(message = "Animal's sex is required!")
    private AnimalSex animalSex;

    @JsonProperty("animalName")
    @NotBlank(message = "Animal's name is required!")
    private String animalName;

    @JsonProperty("animalAge")
    @NotNull(message = "Animal's age is required!")
    private AnimalAge animalAge;

    @JsonProperty("animalSpecie")
    @NotNull(message = "Animal's specie is required!")
    private AnimalSpecie animalSpecie;

    @JsonProperty("animalDescription")
    private String animalDescription;

    @JsonProperty("animalIsCastrated")
    @Field("animalIsCastrated")
    @Builder.Default
    private Boolean animalIsCastrated = false;

    @JsonProperty("animalIsVaccinated")
    @Field("animalIsVaccinated")
    @Builder.Default
    private Boolean animalIsVaccinated = false;

    @JsonProperty("photo")
    private byte[] photo;
}
