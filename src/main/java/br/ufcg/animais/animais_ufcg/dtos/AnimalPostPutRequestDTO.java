package br.ufcg.animais.animais_ufcg.dtos;
import com.fasterxml.jackson.annotation.JsonProperty;
import br.ufcg.animais.animais_ufcg.models.*;
import br.ufcg.animais.animais_ufcg.models.enumerations.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.util.*;

public class AnimalPostPutRequestDTO {

    @JsonProperty("id")
    @Id
    private UUID id;

    @JsonProperty("statusAnimal")
    @Builder.Default
    private AnimalStatus statusAnimal = AnimalStatus.AVALIABLE;

    @JsonProperty("animalSex")
    @NotBlank(message = "Animal's sex is required!")
    private AnimalSex animalSex;

    @JsonProperty("animalName")
    @NotBlank(message = "Animal's name is required!")
    private String animalName;

    @JsonProperty("animalAge")
    @NotBlank(message = "Animal's age is required!")
    private String animalAge;

    @JsonProperty("animalBreed")
    @NotBlank(message = "Animal's breed is required!")
    private String animalBreed;

    @JsonProperty("animalSpecie")
    @NotBlank(message = "Animal's specie is required!")
    private String animalSpecie;

    @JsonProperty("photoURL")
    private String photoURL;
}
