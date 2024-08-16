package br.ufcg.animais.animais_ufcg.models;
import com.fasterxml.jackson.annotation.JsonProperty;
import br.ufcg.animais.animais_ufcg.models.enumerations.AnimalStatus;
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

    @JsonProperty("status")
    @Column(nullable = true)
    private AnimalStatus status;

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

    @PrePersist
    private void setStatus() {
        setStatus(AnimalStatus.AVALIABLE);
    }
}