package br.ufcg.animais.animais_ufcg;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.*;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufcg.animais.animais_ufcg.domain.user.User;
import br.ufcg.animais.animais_ufcg.dtos.login.LoginResponseDTO;
import br.ufcg.animais.animais_ufcg.dtos.adoption_reports.AdoptionReportsResponseDTO;
import br.ufcg.animais.animais_ufcg.models.animals.Animal;
import br.ufcg.animais.animais_ufcg.models.enumerations.*;
import br.ufcg.animais.animais_ufcg.repositories.UserRepository;
import br.ufcg.animais.animais_ufcg.repositories.animals.AnimalsRepository;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Adoption Report's Test Handler")
public class AdoptionReportsTest {

    final String URI_ADOPTION_REPORT = "/adoption_report";
    
    private String AUTH_TOKEN;

    @Autowired
    MockMvc driver;

    @Autowired
    AnimalsRepository animalsRepository;

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void setup() throws Exception {
        String username = "Admin";
        String password = "animaisUFCG";
        String email = "admin@ccc.ufcg.edu.br";

        String jsonRegister = String.format("""
        {
            "name": "%s",
            "email": "%s",
            "password": "%s"
        }""", username, email, password);
        
        driver.perform(post("/auth/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonRegister))
            .andExpect(status().isOk())
            .andReturn();

        MvcResult result1 = driver.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRegister))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = result1.getResponse().getContentAsString();
        LoginResponseDTO loginResponseDTO = new ObjectMapper().readValue(responseBody, LoginResponseDTO.class);
        String token = loginResponseDTO.token();

        this.AUTH_TOKEN = token;
    }

    @AfterEach
    void tearDown() {
        Optional<User> user = userRepository.findByEmail("admin@ccc.ufcg.edu.br");
        userRepository.delete(user);
    }

    @Nested
    @DisplayName("Testing to create adoption reports.")
    class CreateAdoptionReportTests {

        @Test
        @DisplayName("Create a valid adoption report for a animal.")
        void testCreateValidAdoptionReportNoAuthentication() throws Exception {

            Animal randomAdoptedAnimal = animalsRepository.findAll().stream()
                    .filter(animal -> animal.getStatusAnimal() == AnimalStatus.ADOPTED)
                    .findAny()
                    .orElse(null);

            if (randomAdoptedAnimal != null) {
                Object animalID = randomAdoptedAnimal.getId();
                        
                String json =  String.format("""
                    { "animalID": "%s",
                    "animalOwnerName":"Joca",
                    "adoptionReport":"He's great",
                    "photo":"ZXhhbXBsZQ==" } """, animalID);

                    driver.perform(post(URI_ADOPTION_REPORT + "/create")
                    .header("Authorization", "Bearer " + AUTH_TOKEN)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json))
                    .andExpect(status().isBadRequest())
                    .andReturn();
            }
        }

        @Test
        @DisplayName("Create a valid adoption report for a animal.")
        void testCreateValidAdoptionReport() throws Exception {

            Animal randomAdoptedAnimal = animalsRepository.findAll().stream()
                    .filter(animal -> animal.getStatusAnimal() == AnimalStatus.ADOPTED)
                    .findAny()
                    .orElse(null);

            if (randomAdoptedAnimal != null) {
                Object animalID = randomAdoptedAnimal.getId();
                        
                String json =  String.format("""
                    { "animalID": "%s",
                    "animalOwnerName":"Joca",
                    "adoptionReport":"He's great",
                    "photo":"ZXhhbXBsZQ==" } """, animalID);

                MvcResult result = driver.perform(post(URI_ADOPTION_REPORT + "/create")
                .header("Authorization", "Bearer " + AUTH_TOKEN)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andReturn();

                String responseBody = result.getResponse().getContentAsString();
                AdoptionReportsResponseDTO AdoptionReportsResponseDTO = new ObjectMapper().readValue(responseBody, AdoptionReportsResponseDTO.class);
        
                assertAll(
                    () -> assertEquals(AdoptionReportsResponseDTO.getAnimalID(), animalID),
                    () -> assertEquals(AdoptionReportsResponseDTO.getAnimalOwnerName(), "Joca"),
                    () -> assertEquals(AdoptionReportsResponseDTO.getAdoptionReport(), "He's great"),
                    () -> assertEquals(AdoptionReportsResponseDTO.getPhoto(), "ZXhhbXBsZQ==")
                );
            }
        }

        @Test
        @DisplayName("Create a adoption report for a animal, without a photo, it's optional.")
        void testCreateAdoptionReportWithoutPhoto() throws Exception {

            Animal randomAdoptedAnimal = animalsRepository.findAll().stream()
                        .filter(animal -> animal.getStatusAnimal() == AnimalStatus.ADOPTED)
                        .findAny()
                        .orElse(null);

            if (randomAdoptedAnimal != null) {
                Object animalID = randomAdoptedAnimal.getId();
                        
                String json =  String.format("""
                    { "animalID": "%s",
                    "animalOwnerName":"Joca",
                    "adoptionReport":"He's great" } """, animalID);

                MvcResult result = driver.perform(post(URI_ADOPTION_REPORT + "/create")
                    .header("Authorization", "Bearer " + AUTH_TOKEN)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json))
                    .andExpect(status().isCreated())
                    .andReturn();

                String responseBody = result.getResponse().getContentAsString();
                AdoptionReportsResponseDTO AdoptionReportsResponseDTO = new ObjectMapper().readValue(responseBody, AdoptionReportsResponseDTO.class);
        
                assertAll(
                    () -> assertEquals(AdoptionReportsResponseDTO.getAnimalID(), animalID),
                    () -> assertEquals(AdoptionReportsResponseDTO.getAnimalOwnerName(), "Joca"),
                    () -> assertEquals(AdoptionReportsResponseDTO.getAdoptionReport(), "He's great")
                );
            }
        }

        @Test
        @DisplayName("Create a invalid adoption report for a animal.")
        void testCreateAdoptionReportForInexistentAnimal() throws Exception {
            String json = """
                {
                "animalID": "br3imvpkpdsko-w@jjdbvn",
                "animalOwnerName":"Joca",
                "adoptionReport":"He's great",
                "photo":"ZXhhbXBsZQ==" } """;

            driver.perform(post(URI_ADOPTION_REPORT + "/create")
                .header("Authorization", "Bearer " + AUTH_TOKEN)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isNotFound())
                .andReturn();
        }

        @Test
        @DisplayName("Create a invalid adoption report for a animal.")
        void testCreateAdoptionReportWithoutOwnerName() throws Exception {
            
            Animal randomAdoptedAnimal = animalsRepository.findAll().stream()
                    .filter(animal -> animal.getStatusAnimal() == AnimalStatus.ADOPTED)
                    .findAny()
                    .orElse(null);

            if (randomAdoptedAnimal != null) {
                Object animalID = randomAdoptedAnimal.getId();
                        
                String json =  String.format("""
                    { "animalID": "%s",
                    "animalOwnerName":"",
                    "adoptionReport":"He's great",
                    "photo":"ZXhhbXBsZQ==" } """, animalID);

                driver.perform(post(URI_ADOPTION_REPORT + "/create")
                    .header("Authorization", "Bearer " + AUTH_TOKEN)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json))
                    .andExpect(status().isBadRequest())
                    .andReturn();
            }
        }

        @Test
        @DisplayName("Create a invalid adoption report for a animal.")
        void testCreateAdoptionReportWithoutAdoptionReport() throws Exception {

            Animal randomAdoptedAnimal = animalsRepository.findAll().stream()
                        .filter(animal -> animal.getStatusAnimal() == AnimalStatus.ADOPTED)
                        .findAny()
                        .orElse(null);

            if (randomAdoptedAnimal != null) {
                Object animalID = randomAdoptedAnimal.getId();
                        
                String json =  String.format("""
                    { "animalID": "%s",
                    "animalOwnerName":"Joca",
                    "adoptionReport": "",
                    "photo":"ZXhhbXBsZQ==" } """, animalID);

                driver.perform(post(URI_ADOPTION_REPORT + "/create")
                    .header("Authorization", "Bearer " + AUTH_TOKEN)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json))
                    .andExpect(status().isBadRequest())
                    .andReturn();
            }
        }
    }
}
