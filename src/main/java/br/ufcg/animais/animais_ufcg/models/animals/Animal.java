package br.ufcg.animais.animais_ufcg.models.animals;

import com.fasterxml.jackson.annotation.JsonProperty;
import br.ufcg.animais.animais_ufcg.models.enumerations.*;

import org.hibernate.validator.constraints.UUID;
import org.springframework.data.mongodb.core.mapping.*;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "animals")
public class Animal {

    @JsonProperty("id")
    @org.springframework.data.mongodb.core.mapping.Field("_id")
    private UUID id;

    @JsonProperty("statusAnimal")
    @Field("statusAnimal")
    private AnimalStatus statusAnimal = AnimalStatus.AVALIABLE;

    @JsonProperty("animalSex")
    @Field("animalSex")
    private AnimalSex animalSex;

    @JsonProperty("animalName")
    @Field("animalName")
    private String animalName;

    @JsonProperty("animalAge")
    @Field("animalAge")
    private String animalAge;

    @JsonProperty("animalSpecie")
    @Field("animalSpecie")
    private String animalSpecie;
    
    @JsonProperty("animalBreed")
    @Field("animalBreed")
    private String animalBreed = "Unknown";

    @JsonProperty("animalIsCastrated")
    @Field("animalIsCastrated")
    private Boolean animalIsCastrated;

    @JsonProperty("animalIsVaccinated")
    @Field("animalIsVaccinated")
    private Boolean animalIsVaccinated;

    @JsonProperty("photo")
    @Field("photo")
    private byte[] photo;
}