package br.ufcg.animais.animais_ufcg.models.adoption_reports;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.*;
import org.springframework.data.annotation.Id;
import lombok.*;

@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "adoption_report")
public class AdoptionReport {

    @JsonProperty("id")
    @Id
    @Indexed(unique = true)
    private String id;

    @JsonProperty("animalID")
    @Field("animalID")
    private String animalID;

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