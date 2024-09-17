package br.ufcg.animais.animais_ufcg.controller;

import br.ufcg.animais.animais_ufcg.dtos.animals.AnimalPostPutRequestDTO;
import br.ufcg.animais.animais_ufcg.dtos.animals.AnimalResponseDTO;
import br.ufcg.animais.animais_ufcg.exceptions.CustomErrorType;
import br.ufcg.animais.animais_ufcg.models.animals.Animal;
import br.ufcg.animais.animais_ufcg.models.enumerations.AnimalAge;
import br.ufcg.animais.animais_ufcg.models.enumerations.AnimalSex;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Animal Controller Tests")
public class AnimalsControllerTests {

    final String URI_ANIMALS = "/animal";

    @Autowired
    MockMvc driver;

    @Autowired
    AnimalsRepository animalsRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    Animal animal;

    AnimalPostPutRequestDTO animalPostPutRequestDTO;

    String jwtToken;

    @BeforeEach
    void setup() throws Exception {
        objectMapper.registerModule(new JavaTimeModule());

        String loginResponse = driver.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\": \"rohit@gmail.com\", \"password\": \"1234\"}"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        jwtToken = objectMapper.readTree(loginResponse).get("token").asText();



        animal = animalsRepository.save(Animal.builder()
                .statusAnimal(AnimalStatus.AVALIABLE)
                .animalSex(AnimalSex.MALE)
                .animalName("Teste77")
                .animalAge(AnimalAge.YOUNG)
                .animalSpecie("Dog")
                .animalDescription("Descricao qualquer")
                .animalIsCastrated(true)
                .animalIsVaccinated(true)
                .photo("ZXhhbXBsZQ==".getBytes())
                .build()
        );
        animalPostPutRequestDTO = AnimalPostPutRequestDTO.builder()
                .statusAnimal(animal.getStatusAnimal())
                .animalSex(animal.getAnimalSex())
                .animalName(animal.getAnimalName())
                .animalAge(animal.getAnimalAge())
                .animalSpecie(animal.getAnimalSpecie())
                .animalDescription(animal.getAnimalDescription())
                .animalIsCastrated(animal.getAnimalIsCastrated())
                .animalIsVaccinated(animal.getAnimalIsVaccinated())
                .photo(animal.getPhoto())
                .build();
    }

    @AfterEach
    void tearDown() {
        animalsRepository.deleteAll();
    }

    @Nested
    @DisplayName("Set of verification cases for the Rest API basic flows")
    class AnimalRestAPIBasicFlowsVerification {

        @Test
        @DisplayName("When we look for an animal saved by ID")
        void whenWeLookForAnAnimalSavedById() throws Exception {
            // Arrange

            // Act
            String responseJsonString = driver.perform(get(URI_ANIMALS + "/" + animal.getId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(animalPostPutRequestDTO)))
                    .andExpect(status().isOk()) // Codigo 200
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            AnimalResponseDTO resultado = objectMapper.readValue(responseJsonString, new TypeReference<>() {});

            // Assert
            assertAll(
                    () -> assertEquals(animal.getId(), resultado.getId()),
                    () -> assertEquals(animal.getStatusAnimal(), resultado.getStatusAnimal()),
                    () -> assertEquals(animal.getAnimalSex(), resultado.getAnimalSex()),
                    () -> assertEquals(animal.getAnimalName(), resultado.getAnimalName()),
                    () -> assertEquals(animal.getAnimalAge(), resultado.getAnimalAge()),
                    () -> assertEquals(animal.getAnimalSpecie(), resultado.getAnimalSpecie()),
                    () -> assertEquals(animal.getAnimalDescription(), resultado.getAnimalDescription()),
                    () -> assertEquals(animal.getAnimalIsCastrated(), resultado.getAnimalIsCastrated()),
                    () -> assertEquals(animal.getAnimalIsVaccinated(), resultado.getAnimalIsVaccinated()),
                    () -> assertArrayEquals(animal.getPhoto(), resultado.getPhoto())
            );
        }

        @Test
        @DisplayName("When we look for an animal invalid")
        void whenWeLookForAnAnimalInvalid() throws Exception {
            // Arrange

            // Act
            String responseJsonString = driver.perform(get(URI_ANIMALS + "/inexistente")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(animalPostPutRequestDTO)))
                    .andExpect(status().isBadRequest()) // Codigo 400
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            CustomErrorType resultado = objectMapper.readValue(responseJsonString, CustomErrorType.class);

            // Assert
            assertAll(
                    () -> assertEquals("Animal not found!", resultado.getMessage())
            );
        }

    }

}