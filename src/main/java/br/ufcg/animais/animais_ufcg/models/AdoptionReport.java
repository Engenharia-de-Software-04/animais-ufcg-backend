package br.ufcg.animais.animais_ufcg.models;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Getter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "adoptionReport")
public class AdoptionReport {

    @JsonProperty("id")
    @Id
    @GeneratedValue
    private UUID id;

    @JsonProperty("animal")
    @Column(nullable = false)
    private Animal animal;

    @JsonProperty("animalOwerName")
    @Column(nullable = false)
    private String animalOwerName;

    @JsonProperty("report")
    @Column(nullable = false)
    private String report;

    @JsonProperty("photoURL")
    @Column(nullable = true)
    private String photoURL;
}
