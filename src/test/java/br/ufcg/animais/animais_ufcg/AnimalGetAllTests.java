package br.ufcg.animais.animais_ufcg;


import br.ufcg.animais.animais_ufcg.dtos.animals.AnimalPostPutRequestDTO;
import br.ufcg.animais.animais_ufcg.dtos.animals.AnimalResponseDTO;
import br.ufcg.animais.animais_ufcg.exceptions.CustomErrorType;
import br.ufcg.animais.animais_ufcg.models.animals.Animal;
import br.ufcg.animais.animais_ufcg.models.enumerations.AnimalAge;
import br.ufcg.animais.animais_ufcg.models.enumerations.AnimalSex;
import br.ufcg.animais.animais_ufcg.models.enumerations.AnimalSpecie;
import br.ufcg.animais.animais_ufcg.models.enumerations.AnimalStatus;
import br.ufcg.animais.animais_ufcg.repositories.animals.AnimalsRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Animal Controller Tests")
public class AnimalGetAllTests {
    final String URI_ANIMALS = "/animal";

    @Autowired
    MockMvc driver;

    @Autowired
    AnimalsRepository animalsRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    Animal animal1;
    Animal animal2;
    AnimalPostPutRequestDTO animalPostPutRequestDTO1;
    AnimalPostPutRequestDTO animalPostPutRequestDTO2;

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
        animalsRepository.deleteAll();
    }

    @Nested
    @DisplayName("Tests with animals registered")
    class AnimalRestAPIBasicFlowsVerification {
        @Test
        @DisplayName("Test with animals registered")
        void testOneAnimalAvailable() throws Exception {
            animal1 = animalsRepository.save(Animal.builder()
                    .statusAnimal(AnimalStatus.AVAILABLE)
                    .animalSex(AnimalSex.FEMALE)
                    .animalName("TesteAvailable")
                    .animalAge(AnimalAge.YOUNG)
                    .animalSpecie(AnimalSpecie.DOG)
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
                    .statusAnimal(AnimalStatus.ADOPTED)
                    .animalSex(AnimalSex.MALE)
                    .animalName("TesteAvailable2")
                    .animalAge(AnimalAge.ADULT)
                    .animalSpecie(AnimalSpecie.CAT)
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

            String json = driver.perform(get(URI_ANIMALS + "/getAll")
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
            assertAll("Second animal",
                    () -> assertEquals(animal2.getAnimalName(), animals.get(1).getAnimalName()),
                    () -> assertEquals(animal2.getAnimalSex(), animals.get(1).getAnimalSex()),
                    () -> assertEquals(animal2.getAnimalAge(), animals.get(1).getAnimalAge())
            );
        }

        @Test
        @DisplayName("Test with no animal registered")
        void testNoAnimalAvailable() throws Exception {
            String responseJsonString = driver.perform(get(URI_ANIMALS + "/getAll")
                            .header("Authorization", "Bearer " + jwtToken)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();
            CustomErrorType result = objectMapper.readValue(responseJsonString, CustomErrorType.class);

            assertAll(
                    () -> assertEquals("No animals are currently registered", result.getMessage())
            );
        }
    }
}