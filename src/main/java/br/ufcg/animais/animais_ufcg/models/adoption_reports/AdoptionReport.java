package br.ufcg.animais.animais_ufcg.models.adoption_reports;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.*;
import org.springframework.data.annotation.Id;
import lombok.*;
import java.util.*;

@Getter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "adoptionReport")
public class AdoptionReport {

    @JsonProperty("id")
    @Id
    @org.springframework.data.mongodb.core.mapping.Field("_id")
    private String id;

    // @JsonProperty("animal")
    // @Field("animal")
    // private Animal animal;

    @JsonProperty("animalOwnerName")
    @Field("animalOwnerName")
    private String animalOwnerName;

    @JsonProperty("adoptionReport")
    @Field("adoptionReport")
    private String adoptionReport;

    @JsonProperty("photo")
    @Field("photo")
    private byte[] photo;
}
