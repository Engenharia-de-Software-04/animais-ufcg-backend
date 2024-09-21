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
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.ufcg.animais.animais_ufcg.domain.user.User;
import br.ufcg.animais.animais_ufcg.dtos.login.LoginResponseDTO;
import br.ufcg.animais.animais_ufcg.exceptions.CustomErrorType;
import br.ufcg.animais.animais_ufcg.dtos.adoption_reports.AdoptionReportsResponseDTO;
import br.ufcg.animais.animais_ufcg.models.adoption_reports.AdoptionReport;
import br.ufcg.animais.animais_ufcg.models.animals.Animal;
import br.ufcg.animais.animais_ufcg.models.enumerations.*;
import br.ufcg.animais.animais_ufcg.repositories.UserRepository;
import br.ufcg.animais.animais_ufcg.repositories.adoption_reports.AdoptionReportsRepository;
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
    AdoptionReportsRepository adoptionReportsRepository;

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
        @DisplayName("Create a valid adoption report for an animal.")
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
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json))
                    .andExpect(status().isForbidden())
                    .andReturn();
            }
        }

        @Test
        @DisplayName("Create a valid adoption report for an animal.")
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
                AdoptionReportsResponseDTO adoptionReportsResponseDTO = new ObjectMapper().readValue(responseBody, AdoptionReportsResponseDTO.class);
        
                assertAll(
                    () -> assertEquals(animalID, adoptionReportsResponseDTO.getAnimalID()),
                    () -> assertEquals("Joca", adoptionReportsResponseDTO.getAnimalOwnerName()),
                    () -> assertEquals("He's great", adoptionReportsResponseDTO.getAdoptionReport()),
                    () -> assertArrayEquals(Base64.getDecoder().decode("ZXhhbXBsZQ"), adoptionReportsResponseDTO.getPhoto())
                );
            }
        }

        @Test
        @DisplayName("Create a valid adoption report for an animal.")
        void testCreateAdoptionReportForUnadoptedAnimal() throws Exception {

            Animal randomAdoptedAnimal = animalsRepository.findAll().stream()
                    .filter(animal -> animal.getStatusAnimal() == AnimalStatus.AVAILABLE)
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
        @DisplayName("Create a adoption report for an animal, without a photo, it's optional.")
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
                AdoptionReportsResponseDTO adoptionReportsResponseDTO = new ObjectMapper().readValue(responseBody, AdoptionReportsResponseDTO.class);
        
                assertAll(
                    () -> assertEquals(animalID, adoptionReportsResponseDTO.getAnimalID()),
                    () -> assertEquals("Joca", adoptionReportsResponseDTO.getAnimalOwnerName()),
                    () -> assertEquals("He's great", adoptionReportsResponseDTO.getAdoptionReport())
                );
            }
        }

        @Test
        @DisplayName("Create a invalid adoption report for an animal.")
        void testCreateAdoptionReportForInexistentAnimal() throws Exception {
            Object animalID = "bhwy4@yuh3490y9-dgeukbw";
            
            String json = String.format("""
                {
                "animalID": "%s",
                "animalOwnerName":"Joca",
                "adoptionReport":"He's great",
                "photo":"ZXhhbXBsZQ==" } """, animalID);
        
            MvcResult result = driver.perform(post(URI_ADOPTION_REPORT + "/create")
                .header("Authorization", "Bearer " + AUTH_TOKEN)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest())
                .andReturn();
        
            String responseBody = result.getResponse().getContentAsString();
        
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            CustomErrorType error = mapper.readValue(responseBody, CustomErrorType.class);
        
            assertEquals("Animal not found!", error.getMessage());
        }

        @Test
        @DisplayName("Create a invalid adoption report for an animal.")
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
        @DisplayName("Create a invalid adoption report for an animal.")
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

    @Nested
    @DisplayName("Testing to get an adoption report.")
    class GetAdoptionReportTests {

        @Test
        @DisplayName("Get an adoption report for an animal, with no authentication.")
        void testGetAdoptionReportNoAuthentication() throws Exception {

            AdoptionReport randomAdoptionReport = adoptionReportsRepository
                    .findAll().stream().findAny()
                    .orElse(null);

            if (randomAdoptionReport != null) {
                Object adoptionReportID = randomAdoptionReport.getId();
                        
                driver.perform(get(URI_ADOPTION_REPORT + "/get/" + adoptionReportID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
            }
        }

        @Test
        @DisplayName("Get an adoption report for an animal, authenticated.")
        void testGetAdoptionReportAuthenticaticated() throws Exception {

            AdoptionReport randomAdoptionReport = adoptionReportsRepository
                    .findAll().stream().findAny()
                    .orElse(null);

            if (randomAdoptionReport != null) {
                Object adoptionReportID = randomAdoptionReport.getId();
                        
                driver.perform(get(URI_ADOPTION_REPORT + "/get/" + adoptionReportID)
                    .header("Authorization", "Bearer " + AUTH_TOKEN)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn();
            }
        }

        @Test
        @DisplayName("Get an inexistent adoption report, with no authentication.")
        void testGetAdoptionReportNoAuthenticated2() throws Exception {

            Object adoptionReportID = "bhwy4@yuh3490y9-dgeukbw";
                        
            driver.perform(get(URI_ADOPTION_REPORT + "/get/%s", adoptionReportID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();
        }

        @Test
        @DisplayName("Get an inexistent adoption report, with no authentication.")
        void testGetAdoptionReportAuthenticaticated2() throws Exception {

            Object adoptionReportID = "bhwy4@yuh3490y9-dgeukbw";
                        
            driver.perform(get(URI_ADOPTION_REPORT + "/get/%s", adoptionReportID)
                .header("Authorization", "Bearer " + AUTH_TOKEN)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();
        }
    }
}
