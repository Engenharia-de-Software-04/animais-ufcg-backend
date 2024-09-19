package br.ufcg.animais.animais_ufcg;

import br.ufcg.animais.animais_ufcg.dto.animals.AdoptionReportsPostPutRequestDTO;
import br.ufcg.animais.animais_ufcg.dto.animals.AnimalPostPutRequestDTO;
import br.ufcg.animais.animais_ufcg.models.adoption_reports.AdoptionReport;
import br.ufcg.animais.animais_ufcg.models.animals.Animal;
import br.ufcg.animais.animais_ufcg.models.enumerations.AnimalAge;
import br.ufcg.animais.animais_ufcg.models.enumerations.AnimalSex;
import br.ufcg.animais.animais_ufcg.models.enumerations.AnimalStatus;
import br.ufcg.animais.animais_ufcg.repositories.adoption_reports.AdoptionReportsRepository;
import br.ufcg.animais.animais_ufcg.repositories.animals.AnimalsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Animal Controller Tests")
public class GetAllAdoptionRepostsTests {
    final String URI_ANIMALS = "/animal";

    @Autowired
    MockMvc driver;

    @Autowired
    AnimalsRepository animalsRepository;

    @Autowired
    AdoptionReportsRepository adoptionReportsRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    Animal animal1;
    Animal animal2;

    AnimalPostPutRequestDTO animalPostPutRequestDTO1;
    AnimalPostPutRequestDTO animalPostPutRequestDTO2;

    AdoptionReport report1;
    AdoptionReport report2;

    AdoptionReportsPostPutRequestDTO adoptionReportsPostPutRequestDTO1;
    AdoptionReportsPostPutRequestDTO adoptionReportsPostPutRequestDTO2;

    String jwtToken;

    @BeforeEach
    void setup() throws Exception {
        objectMapper.registerModule(new JavaTimeModule());

        String loginResponse = driver.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\": \"welly@ccc.ufcg.edu.br\", \"password\": \"12345\"}"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        jwtToken = objectMapper.readTree(loginResponse).get("token").asText();

    }

    @AfterEach
    void tearDown() {
        adoptionReportsRepository.deleteAll();
        animalsRepository.deleteAll();
    }

    @Nested
    @DisplayName("Tests with animals available")
    class AnimalRestAPIBasicFlowsVerification {
        @Test
        @DisplayName("Test with only one animal available")
        void testOneAnimalAvailable() throws Exception {
            animal1 = animalsRepository.save(Animal.builder()
                    .statusAnimal(AnimalStatus.AVAILABLE)
                    .animalSex(AnimalSex.FEMALE)
                    .animalName("Nala")
                    .animalAge(AnimalAge.YOUNG)
                    .animalSpecie("DOG")
                    .animalDescription("teste")
                    .animalIsCastrated(false)
                    .animalIsVaccinated(false)
                    .photo("ZXhhbXBsZQ==".getBytes())
                    .build()
            );

            animalPostPutRequestDTO1 = AnimalPostPutRequestDTO.builder()
                    .statusAnimal(animal1.getStatusAnimal())
                    .animalSex(animal1.getAnimalSex())
                    .animalName(animal1.getAnimalName())
                    .animalAge(animal1.getAnimalAge())
                    .animalSpecie(animal1.getAnimalSpecie())
                    .animalDescription(animal1.getAnimalDescription())
                    .animalIsCastrated(animal1.getAnimalIsCastrated())
                    .animalIsVaccinated(animal1.getAnimalIsVaccinated())
                    .photo(animal1.getPhoto())
                    .build();

            animal2 = animalsRepository.save(Animal.builder()
                    .statusAnimal(AnimalStatus.AVAILABLE)
                    .animalSex(AnimalSex.MALE)
                    .animalName("Teddy")
                    .animalAge(AnimalAge.SENIOR)
                    .animalSpecie("CAT")
                    .animalDescription("teste")
                    .animalIsCastrated(false)
                    .animalIsVaccinated(false)
                    .photo("ZXhhbXBsZQ==".getBytes())
                    .build()
            );

            animalPostPutRequestDTO2 = AnimalPostPutRequestDTO.builder()
                    .statusAnimal(animal2.getStatusAnimal())
                    .animalSex(animal2.getAnimalSex())
                    .animalName(animal2.getAnimalName())
                    .animalAge(animal2.getAnimalAge())
                    .animalSpecie(animal2.getAnimalSpecie())
                    .animalDescription(animal2.getAnimalDescription())
                    .animalIsCastrated(animal2.getAnimalIsCastrated())
                    .animalIsVaccinated(animal2.getAnimalIsVaccinated())
                    .photo(animal2.getPhoto())
                    .build();

            report1 = adoptionReportsRepository.save(AdoptionReport.builder()
                    .animal(animal1)
                    .animalOwnerName("Nala")
                    .adoptionReport("She's pretty")
                    .photo("ZXhhbXBsZQ==".getBytes())
                    .build()
            );

            adoptionReportsPostPutRequestDTO1 = AdoptionReportsPostPutRequestDTO.builder()
                    .animal(report1.getAnimal())
                    .animalOwnerName(report1.getAnimalOwnerName())
                    .adoptionReport(report1.getAdoptionReport())
                    .photo(report1.getPhoto())
                    .build();

            report2 = adoptionReportsRepository.save(AdoptionReport.builder()
                    .animal(animal2)
                    .animalOwnerName("Teddy")
                    .adoptionReport("He's so funny")
                    .photo("ZXhhbXBsZQ==".getBytes())
                    .build()
            );

            adoptionReportsPostPutRequestDTO1 = AdoptionReportsPostPutRequestDTO.builder()
                    .animal(report2.getAnimal())
                    .animalOwnerName(report2.getAnimalOwnerName())
                    .adoptionReport(report2.getAdoptionReport())
                    .photo(report2.getPhoto())
                    .build();

            String json = driver.perform(get(URI_ANIMALS + "/getAvailable")
                            .header("Authorization", "Bearer " + jwtToken)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();
            List<AnimalResponseDTO> animals = objectMapper.readValue(json, new TypeReference<List<AnimalResponseDTO>>() {
            });


            assertAll("One animal",
                    () -> assertFalse(animals.isEmpty()),
                    () -> assertEquals(animal1.getAnimalName(), animals.get(0).getAnimalName()),
                    () -> assertEquals(animal1.getAnimalSex(), animals.get(0).getAnimalSex()),
                    () -> assertEquals(animal1.getAnimalAge(), animals.get(0).getAnimalAge())
            );
        }

    }
