package br.ufcg.animais.animais_ufcg.models.animals;

import com.fasterxml.jackson.annotation.JsonProperty;
import br.ufcg.animais.animais_ufcg.models.enumerations.*;
import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.*;
import lombok.*;

@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "animals")
public class Animal {

    @JsonProperty("id")
    @Id
    @Indexed(unique = true)
    private String id;

    @JsonProperty("statusAnimal")
    @Field("statusAnimal")
    @Builder.Default
    private AnimalStatus statusAnimal = AnimalStatus.AVALIABLE;

    @JsonProperty("animalSex")
    @Field("animalSex")
    private AnimalSex animalSex;

    @JsonProperty("animalName")
    @Field("animalName")
    private String animalName;

    @JsonProperty("animalAge")
    @Field("animalAge")
    private AnimalAge animalAge;

    @JsonProperty("animalSpecie")
    @Field("animalSpecie")
    private String animalSpecie;

    @JsonProperty("animalDescription")
    @Field("animalDescription")
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
    @Field("photo")
    private byte[] photo;
}