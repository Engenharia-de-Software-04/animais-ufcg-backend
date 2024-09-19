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
import br.ufcg.animais.animais_ufcg.dtos.animals.*;
import br.ufcg.animais.animais_ufcg.models.enumerations.*;
import br.ufcg.animais.animais_ufcg.repositories.UserRepository;
import br.ufcg.animais.animais_ufcg.repositories.animals.AnimalsRepository;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Animal's Test Handler")
public class AnimalsTest {
    
    final String URI_ANIMALS = "/animal";
    
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
    @DisplayName("Testing to create animals.")
    class CreateAnimalTests {

        @Test
        @DisplayName("Testing to create animal with valid data. (DOG | MALE | YOUNG)")
        void testCreateValidAnimalWithNoAuthentication() throws Exception {
            String json = """
                {   "animalName": "Thor",
                    "animalAge": "YOUNG",
                    "animalSex": "MALE",
                    "animalSpecie": "DOG",
                    "animalDescription": "A cute dog.",
                    "animalIsCastrated": true,
                    "animalIsVaccinated": true } """;

            driver.perform(post(URI_ANIMALS + "/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isForbidden())
                .andReturn();
        }

        @Test
        @DisplayName("Testing to create animal with valid data. (DOG | MALE | YOUNG)")
        void testCreateValidAnimal() throws Exception {
            String json = """
                {   "animalName": "Thor",
                    "animalAge": "YOUNG",
                    "animalSex": "MALE",
                    "animalSpecie": "DOG",
                    "animalDescription": "A cute dog.",
                    "animalIsCastrated": true,
                    "animalIsVaccinated": true } """;

            MvcResult result = driver.perform(post(URI_ANIMALS + "/create")
            .header("Authorization", "Bearer " + AUTH_TOKEN)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isCreated())
            .andReturn();

            String responseBody = result.getResponse().getContentAsString();
            AnimalResponseDTO animalResponseDTO = new ObjectMapper().readValue(responseBody, AnimalResponseDTO.class);
            
            assertAll(
                () -> assertEquals("Thor", animalResponseDTO.getAnimalName()),
                () -> assertEquals(AnimalAge.YOUNG, animalResponseDTO.getAnimalAge()),
                () -> assertEquals(AnimalSex.MALE, animalResponseDTO.getAnimalSex()),
                () -> assertEquals(AnimalSpecie.DOG, animalResponseDTO.getAnimalSpecie()),
                () -> assertEquals("A cute dog.", animalResponseDTO.getAnimalDescription()),
                () -> assertTrue(animalResponseDTO.getAnimalIsCastrated()),
                () -> assertTrue(animalResponseDTO.getAnimalIsVaccinated())
            );
        }

        @Test
        @DisplayName("Testing to create animal with valid data. (CAT | FEMALE | ADULT)")
        void testCreateValidAnimal2() throws Exception {
            String json = """
                {   "animalName": "Kiara",
                    "animalAge": "ADULT",
                    "animalSex": "FEMALE",
                    "animalSpecie": "CAT",
                    "animalDescription": "A cute cat.",
                    "animalIsCastrated": true,
                    "animalIsVaccinated": true } """;

            MvcResult result = driver.perform(post(URI_ANIMALS + "/create")
            .header("Authorization", "Bearer " + AUTH_TOKEN)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isCreated())
            .andReturn();

            String responseBody = result.getResponse().getContentAsString();
            AnimalResponseDTO animalResponseDTO = new ObjectMapper().readValue(responseBody, AnimalResponseDTO.class);
            
            assertAll(
                () -> assertEquals("Kiara", animalResponseDTO.getAnimalName()),
                () -> assertEquals(AnimalAge.ADULT, animalResponseDTO.getAnimalAge()),
                () -> assertEquals(AnimalSex.FEMALE, animalResponseDTO.getAnimalSex()),
                () -> assertEquals(AnimalSpecie.CAT, animalResponseDTO.getAnimalSpecie()),
                () -> assertEquals("A cute cat.", animalResponseDTO.getAnimalDescription()),
                () -> assertTrue(animalResponseDTO.getAnimalIsCastrated()),
                () -> assertTrue(animalResponseDTO.getAnimalIsVaccinated())
            );
        }

        @Test
        @DisplayName("Testing to create animal with valid data. (CAT | FEMALE | SENIOR)")
        void testCreateValidAnimal3() throws Exception {
            String json = """
                {   "animalName": "Kitty",
                    "animalAge": "SENIOR",
                    "animalSex": "FEMALE",
                    "animalSpecie": "CAT",
                    "animalDescription": "A cute cat.",
                    "animalIsCastrated": true,
                    "animalIsVaccinated": true } """;

            MvcResult result = driver.perform(post(URI_ANIMALS + "/create")
            .header("Authorization", "Bearer " + AUTH_TOKEN)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isCreated())
            .andReturn();

            String responseBody = result.getResponse().getContentAsString();
            AnimalResponseDTO animalResponseDTO = new ObjectMapper().readValue(responseBody, AnimalResponseDTO.class);
            
            assertAll(
                () -> assertEquals("Kitty", animalResponseDTO.getAnimalName()),
                () -> assertEquals(AnimalAge.SENIOR, animalResponseDTO.getAnimalAge()),
                () -> assertEquals(AnimalSex.FEMALE, animalResponseDTO.getAnimalSex()),
                () -> assertEquals(AnimalSpecie.CAT, animalResponseDTO.getAnimalSpecie()),
                () -> assertEquals("A cute cat.", animalResponseDTO.getAnimalDescription()),
                () -> assertTrue(animalResponseDTO.getAnimalIsCastrated()),
                () -> assertTrue(animalResponseDTO.getAnimalIsVaccinated())
            );
        }

        @Test
        @DisplayName("Testing to create animal with valid data. (OTHER | FEMALE | SENIOR)")
        void testCreateValidAnimal4() throws Exception {
            String json = """
                {   "animalName": "Kitty",
                    "animalAge": "SENIOR",
                    "animalSex": "FEMALE",
                    "animalSpecie": "OTHER",
                    "animalDescription": "A cute cat.",
                    "animalIsCastrated": true,
                    "animalIsVaccinated": true } """;

            MvcResult result = driver.perform(post(URI_ANIMALS + "/create")
                .header("Authorization", "Bearer " + AUTH_TOKEN)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andReturn();

            String responseBody = result.getResponse().getContentAsString();
            AnimalResponseDTO animalResponseDTO = new ObjectMapper().readValue(responseBody, AnimalResponseDTO.class);
            
            assertAll(
                () -> assertEquals("Kitty", animalResponseDTO.getAnimalName()),
                () -> assertEquals(AnimalAge.SENIOR, animalResponseDTO.getAnimalAge()),
                () -> assertEquals(AnimalSex.FEMALE, animalResponseDTO.getAnimalSex()),
                () -> assertEquals(AnimalSpecie.OTHER, animalResponseDTO.getAnimalSpecie()),
                () -> assertEquals("A cute cat.", animalResponseDTO.getAnimalDescription()),
                () -> assertTrue(animalResponseDTO.getAnimalIsCastrated()),
                () -> assertTrue(animalResponseDTO.getAnimalIsVaccinated())
            );
        }

        @Test
        @DisplayName("Testing to create animal with invalid name.")
        void testAnimalWithoutName() throws Exception{
            String json = """
                {   "animalName": "",
                    "animalAge": "ADULT",
                    "animalSex": "FEMALE",
                    "animalSpecie": "CAT",
                    "animalDescription": "A cute cat.",
                    "animalIsCastrated": true,
                    "animalIsVaccinated": true } """;

            driver.perform(post(URI_ANIMALS + "/create")
            .header("Authorization", "Bearer " + AUTH_TOKEN)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isBadRequest())
            .andReturn();
        }

        @Test
        @DisplayName("Testing to create animal with null age.")
        void testAnimalInvalidAge() throws Exception {
            String json = """
                {   "animalName": "Kiara",
                    "animalAge": "",
                    "animalSex": "FEMALE",
                    "animalSpecie": "CAT",
                    "animalDescription": "A cute cat.",
                    "animalIsCastrated": true,
                    "animalIsVaccinated": true } """;

            driver.perform(post(URI_ANIMALS + "/create")
            .header("Authorization", "Bearer " + AUTH_TOKEN)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isBadRequest())
            .andReturn();
        }

        @Test
        @DisplayName("Testing to create animal with invalid (filhote) age.")
        void testAnimalInvalidAge2() throws Exception {
            String json = """
                {   "animalName": "Kiara",
                    "animalAge": "filhote",
                    "animalSex": "FEMALE",
                    "animalSpecie": "CAT",
                    "animalDescription": "A cute cat.",
                    "animalIsCastrated": true,
                    "animalIsVaccinated": true } """;

            driver.perform(post(URI_ANIMALS + "/create")
            .header("Authorization", "Bearer " + AUTH_TOKEN)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isBadRequest())
            .andReturn();
        }

        @Test
        @DisplayName("Testing to create animal with null specie.")
        void testAnimalInvalidSpecie() throws Exception{
            String json = """
                {   "animalName": "Kiara",
                    "animalAge": "ADULT",
                    "animalSex": "FEMALE",
                    "animalSpecie": "",
                    "animalDescription": "A cute cat.",
                    "animalIsCastrated": true,
                    "animalIsVaccinated": true } """;

            driver.perform(post(URI_ANIMALS + "/create")
            .header("Authorization", "Bearer " + AUTH_TOKEN)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isBadRequest())
            .andReturn();
        }

        @Test
        @DisplayName("Testing to create animal with invalid (gata) specie.")
        void testAnimalInvalidSpecie2() throws Exception{
            String json = """
                {   "animalName": "Kiara",
                    "animalAge": "ADULT",
                    "animalSex": "FEMALE",
                    "animalSpecie": "gata",
                    "animalDescription": "A cute cat.",
                    "animalIsCastrated": true,
                    "animalIsVaccinated": true } """;

            driver.perform(post(URI_ANIMALS + "/create")
            .header("Authorization", "Bearer " + AUTH_TOKEN)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isBadRequest())
            .andReturn();
        }
        
        @Test
        @DisplayName("Testing to create animal with null sex.")
        void testAnimalInvalidSex() throws Exception{
            String json = """
            {   "animalName": "Kitty",
            "animalAge": "SENIOR",
            "animalSex": "",
            "animalSpecie": "OTHER",
            "animalDescription": "A cute cat.",
            "animalIsCastrated": true,
            "animalIsVaccinated": true } """;


            driver.perform(post(URI_ANIMALS + "/create")
            .header("Authorization", "Bearer " + AUTH_TOKEN)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isBadRequest())
            .andReturn();
        }

        @Test
        @DisplayName("Testing to create animal with invalid (femea) sex.")
        void testAnimalInvalidSex2() throws Exception{
            String json = """
            {   "animalName": "Kitty",
            "animalAge": "SENIOR",
            "animalSex": "femea",
            "animalSpecie": "OTHER",
            "animalDescription": "A cute cat.",
            "animalIsCastrated": true,
            "animalIsVaccinated": true } """;


            driver.perform(post(URI_ANIMALS + "/create")
            .header("Authorization", "Bearer " + AUTH_TOKEN)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isBadRequest())
            .andReturn();
        }

        @Test
        @DisplayName("Testing to create animal with invalid status, has a default.")
        void testAnimalInvalidStatus() throws Exception{
            String json = """
            {   "animalStatus" : "disponivel",
            "animalName": "Kitty",
            "animalAge": "SENIOR",
            "animalSex": "FEMALE",
            "animalSpecie": "OTHER",
            "animalDescription": "A cute cat.",
            "animalIsCastrated": true,
            "animalIsVaccinated": true } """;

            driver.perform(post(URI_ANIMALS + "/create")
            .header("Authorization", "Bearer " + AUTH_TOKEN)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isCreated())
            .andReturn();
        }

        @Test
        @DisplayName("Testing to create animal with optional status, has a default.")
        void testAnimalWithStatusAdopted() throws Exception{
            String json = """
            {   "animalStatus" : "ADOPTED",
            "animalName": "Kitty",
            "animalAge": "SENIOR",
            "animalSex": "FEMALE",
            "animalSpecie": "OTHER",
            "animalDescription": "A cute cat.",
            "animalIsCastrated": true,
            "animalIsVaccinated": true } """;

            driver.perform(post(URI_ANIMALS + "/create")
            .header("Authorization", "Bearer " + AUTH_TOKEN)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isCreated())
            .andReturn();
        }

        @Test
        @DisplayName("Testing to create animal with optional castration, has a default.")
        void testAnimalWithoutCastration() throws Exception{
            String json = """
            {   "animalName": "Kitty",
            "animalAge": "SENIOR",
            "animalSex": "FEMALE",
            "animalSpecie": "OTHER",
            "animalDescription": "A cute cat.",
            "animalIsVaccinated": true } """;

            driver.perform(post(URI_ANIMALS + "/create")
            .header("Authorization", "Bearer " + AUTH_TOKEN)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isCreated())
            .andReturn();
        }

        @Test
        @DisplayName("Testing to create animal with optional vaccination, has a default.")
        void testAnimalWithoutVaccination() throws Exception{
            String json = """
            {   "animalName": "Kitty",
            "animalAge": "SENIOR",
            "animalSex": "FEMALE",
            "animalSpecie": "OTHER",
            "animalDescription": "A cute cat.",
            "animalIsCastrated": true } """;

            driver.perform(post(URI_ANIMALS + "/create")
            .header("Authorization", "Bearer " + AUTH_TOKEN)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isCreated())
            .andReturn();
        }

        @Test
        @DisplayName("Testing to create animal with optional description, has a default.")
        void testAnimalWithoutDescription() throws Exception{
            String json = """
            {   "animalName": "Kitty",
            "animalAge": "SENIOR",
            "animalSex": "FEMALE",
            "animalSpecie": "OTHER",
            "animalIsCastrated": true,
            "animalIsVaccinated": true } """;

            driver.perform(post(URI_ANIMALS + "/create")
            .header("Authorization", "Bearer " + AUTH_TOKEN)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isCreated())
            .andReturn();
        }
    }
}
