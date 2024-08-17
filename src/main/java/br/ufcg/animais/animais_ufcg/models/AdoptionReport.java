package br.ufcg.animais.animais_ufcg.models;
import br.ufcg.animais.animais_ufcg.models.*;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@Document(name = "adoptionReport")
public class AdoptionReport {

    @JsonProperty("id")
    @Id
    @org.springframework.data.mongodb.core.mapping.Field("_id")
    private UUID id;

    @JsonProperty("animal")
    @Field("animal")
    private Animal animal;

    @JsonProperty("animalOwerName")
    @Field("animalOwerName")
    private String animalOwerName;

    @JsonProperty("report")
    @Field("report")
    private String report;

    @JsonProperty("photoURL")
    @Field("photoURL")
    private String photoURL;
}
