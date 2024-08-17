package br.ufcg.animais.animais_ufcg.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import br.ufcg.animais.animais_ufcg.models.enumerations.*;
import org.springframework.data.mongodb.core.mapping.*;
import org.springframework.data.annotation.Id;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Getter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "animals")
public class Animal {

    @JsonProperty("id")
    @Id
    @org.springframework.data.mongodb.core.mapping.Field("_id")
    private UUID id;

    @JsonProperty("statusAnimal")
    @Field("statusAnimal")
    private AnimalStatus statusAnimal;

    @JsonProperty("animalSex")
    @Field("animalSex")
    private AnimalSex animalSex;

    @JsonProperty("animalName")
    @Field("animalName")
    private String animalName;

    @JsonProperty("animalAge")
    @Field("animalAge")
    private String animalAge;

    @JsonProperty("animalBreed")
    @Field("animalBreed")
    private String animalBreed;

    @JsonProperty("animalSpecie")
    @Field("animalSpecie")
    private String animalSpecie;

    @JsonProperty("photoURL")
    @Field("photoURL")
    private String photoURL;

    @PrePersist
    private void setStatus() {
        setStatus(AnimalStatus.AVALIABLE);
    }
}