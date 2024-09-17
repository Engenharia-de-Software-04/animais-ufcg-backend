package br.ufcg.animais.animais_ufcg.dtos.animals;

import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.ufcg.animais.animais_ufcg.models.animals.*;
import br.ufcg.animais.animais_ufcg.models.enumerations.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnimalResponseDTO {

    @JsonProperty("id")
    private String id;

    @JsonProperty("statusAnimal")
    @Builder.Default
    private AnimalStatus statusAnimal = AnimalStatus.AVAILABLE;

    @JsonProperty("animalSex")
    @NotBlank(message = "Animal's sex is required!")
    private AnimalSex animalSex;

    @JsonProperty("animalName")
    @NotBlank(message = "Animal's name is required!")
    private String animalName;

    @JsonProperty("animalAge")
    @NotBlank(message = "Animal's age is required!")
    private AnimalAge animalAge;

    @JsonProperty("animalSpecie")
    @NotBlank(message = "Animal's specie is required!")
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

    public AnimalResponseDTO (Animal animal) {
        this.id = animal.getId();
        this.statusAnimal = animal.getStatusAnimal();
        this.animalSex = animal.getAnimalSex();
        this.animalName = animal.getAnimalName();
        this.animalAge = animal.getAnimalAge();
        this.animalDescription = animal.getAnimalDescription();
        this.animalSpecie = animal.getAnimalSpecie();
        this.animalIsCastrated = animal.getAnimalIsCastrated();
        this.animalIsVaccinated = animal.getAnimalIsVaccinated();
        this.photo = animal.getPhoto();
    }
}