package br.ufcg.animais.animais_ufcg.models;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.ufcg.animais.animais_ufcg.models.enumerations.*;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Getter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "animals")
public class Animal {

    @JsonProperty("id")
    @Id
    @GeneratedValue
    private UUID id;

    @JsonProperty("statusAnimal")
    @Column(nullable = true)
    private AnimalStatus statusAnimal;

    @JsonProperty("animalSex")
    @Column(nullable = false)
    private AnimalSex animalSex;

    @JsonProperty("animalName")
    @Column(nullable = false)
    private String animalName;

    @JsonProperty("animalAge")
    @Column(nullable = false)
    private String animalAge;

    @JsonProperty("animalBreed")
    @Column(nullable = false)
    private String animalBreed;

    @JsonProperty("animalSpecie")
    @Column(nullable = false)
    private String animalSpecie;

    @JsonProperty("photoURL")
    @Column(nullable = true)
    private String photoURL;

    @PrePersist
    private void setStatus() {
        setStatus(AnimalStatus.AVALIABLE);
    }
}