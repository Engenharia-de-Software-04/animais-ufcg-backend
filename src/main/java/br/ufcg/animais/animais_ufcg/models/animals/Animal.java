package br.ufcg.animais.animais_ufcg.models.animals;

import com.fasterxml.jackson.annotation.JsonProperty;
import br.ufcg.animais.animais_ufcg.models.enumerations.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
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
    private Boolean animalIsCastrated;

    @JsonProperty("animalIsVaccinated")
    @Field("animalIsVaccinated")
    private Boolean animalIsVaccinated;

    @JsonProperty("photo")
    @Field("photo")
    private byte[] photo;
}