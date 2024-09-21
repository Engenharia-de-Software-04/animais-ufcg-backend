package br.ufcg.animais.animais_ufcg.diffblue_controllers.animals;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import br.ufcg.animais.animais_ufcg.dtos.animals.AnimalPostPutRequestDTO;
import br.ufcg.animais.animais_ufcg.dtos.animals.AnimalResponseDTO;
import br.ufcg.animais.animais_ufcg.models.enumerations.AnimalAge;
import br.ufcg.animais.animais_ufcg.models.enumerations.AnimalSex;
import br.ufcg.animais.animais_ufcg.models.enumerations.AnimalSpecie;
import br.ufcg.animais.animais_ufcg.models.enumerations.AnimalStatus;
import br.ufcg.animais.animais_ufcg.services.animals.AnimalService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {AnimalsController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class AnimalsControllerDiffblueTest {
    @MockBean
    private AnimalService animalService;

    @Autowired
    private AnimalsController animalsController;

    /**
     * Method under test:
     * {@link AnimalsController#creatingAnimal(AnimalPostPutRequestDTO)}
     */
    @Test
    void testCreatingAnimal() throws Exception {
        // Arrange
        AnimalResponseDTO.AnimalResponseDTOBuilder idResult = AnimalResponseDTO.builder()
                .animalAge(AnimalAge.YOUNG)
                .animalDescription("Animal Description")
                .animalName("Animal Name")
                .animalSex(AnimalSex.FEMALE)
                .animalSpecie(AnimalSpecie.DOG)
                .id("42");
        AnimalResponseDTO buildResult = idResult.photo("AXAXAXAX".getBytes("UTF-8")).build();
        when(animalService.creatingAnimal(Mockito.<AnimalPostPutRequestDTO>any())).thenReturn(buildResult);

        AnimalPostPutRequestDTO animalPostPutRequestDTO = new AnimalPostPutRequestDTO();
        animalPostPutRequestDTO.setAnimalAge(AnimalAge.YOUNG);
        animalPostPutRequestDTO.setAnimalDescription("Animal Description");
        animalPostPutRequestDTO.setAnimalIsCastrated(true);
        animalPostPutRequestDTO.setAnimalIsVaccinated(true);
        animalPostPutRequestDTO.setAnimalName("Animal Name");
        animalPostPutRequestDTO.setAnimalSex(AnimalSex.FEMALE);
        animalPostPutRequestDTO.setAnimalSpecie(AnimalSpecie.DOG);
        animalPostPutRequestDTO.setId("42");
        animalPostPutRequestDTO.setPhoto("AXAXAXAX".getBytes("UTF-8"));
        animalPostPutRequestDTO.setStatusAnimal(AnimalStatus.ADOPTED);
        String content = (new ObjectMapper()).writeValueAsString(animalPostPutRequestDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/animal/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(animalsController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":\"42\",\"statusAnimal\":\"AVAILABLE\",\"animalSex\":\"FEMALE\",\"animalName\":\"Animal Name\",\"animalAge\":"
                                        + "\"YOUNG\",\"animalSpecie\":\"DOG\",\"animalDescription\":\"Animal Description\",\"animalIsCastrated\":false,"
                                        + "\"animalIsVaccinated\":false,\"photo\":\"QVhBWEFYQVg=\"}"));
    }

    /**
     * Method under test: {@link AnimalsController#deleteAnimal(String)}
     */
    @Test
    void testDeleteAnimal() throws Exception {
        // Arrange
        doNothing().when(animalService).deleteAnimal(Mockito.<String>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/animal/{id}", "42");

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(animalsController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string(""));
    }

    /**
     * Method under test: {@link AnimalsController#deleteAnimal(String)}
     */
    @Test
    void testDeleteAnimal2() throws Exception {
        // Arrange
        doNothing().when(animalService).deleteAnimal(Mockito.<String>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/animal/{id}", "42");
        requestBuilder.contentType("https://example.org/example");

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(animalsController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string(""));
    }

    /**
     * Method under test: {@link AnimalsController#getAnimalById(String)}
     */
    @Test
    void testGetAnimalById() throws Exception {
        // Arrange
        AnimalResponseDTO.AnimalResponseDTOBuilder idResult = AnimalResponseDTO.builder()
                .animalAge(AnimalAge.YOUNG)
                .animalDescription("Animal Description")
                .animalName("Animal Name")
                .animalSex(AnimalSex.FEMALE)
                .animalSpecie(AnimalSpecie.DOG)
                .id("42");
        AnimalResponseDTO buildResult = idResult.photo("AXAXAXAX".getBytes("UTF-8")).build();
        when(animalService.getAnimalById(Mockito.<String>any())).thenReturn(buildResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/animal/{id}", "42");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(animalsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":\"42\",\"statusAnimal\":\"AVAILABLE\",\"animalSex\":\"FEMALE\",\"animalName\":\"Animal Name\",\"animalAge\":"
                                        + "\"YOUNG\",\"animalSpecie\":\"DOG\",\"animalDescription\":\"Animal Description\",\"animalIsCastrated\":false,"
                                        + "\"animalIsVaccinated\":false,\"photo\":\"QVhBWEFYQVg=\"}"));
    }

    /**
     * Method under test:
     * {@link AnimalsController#updateAnimal(String, AnimalPostPutRequestDTO)}
     */
    @Test
    void testUpdateAnimal() throws Exception {
        // Arrange
        AnimalResponseDTO.AnimalResponseDTOBuilder idResult = AnimalResponseDTO.builder()
                .animalAge(AnimalAge.YOUNG)
                .animalDescription("Animal Description")
                .animalName("Animal Name")
                .animalSex(AnimalSex.FEMALE)
                .animalSpecie(AnimalSpecie.DOG)
                .id("42");
        AnimalResponseDTO buildResult = idResult.photo("AXAXAXAX".getBytes("UTF-8")).build();
        when(animalService.updateAnimal(Mockito.<String>any(), Mockito.<AnimalPostPutRequestDTO>any()))
                .thenReturn(buildResult);

        AnimalPostPutRequestDTO animalPostPutRequestDTO = new AnimalPostPutRequestDTO();
        animalPostPutRequestDTO.setAnimalAge(AnimalAge.YOUNG);
        animalPostPutRequestDTO.setAnimalDescription("Animal Description");
        animalPostPutRequestDTO.setAnimalIsCastrated(true);
        animalPostPutRequestDTO.setAnimalIsVaccinated(true);
        animalPostPutRequestDTO.setAnimalName("Animal Name");
        animalPostPutRequestDTO.setAnimalSex(AnimalSex.FEMALE);
        animalPostPutRequestDTO.setAnimalSpecie(AnimalSpecie.DOG);
        animalPostPutRequestDTO.setId("42");
        animalPostPutRequestDTO.setPhoto("AXAXAXAX".getBytes("UTF-8"));
        animalPostPutRequestDTO.setStatusAnimal(AnimalStatus.ADOPTED);
        String content = (new ObjectMapper()).writeValueAsString(animalPostPutRequestDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/animal/update/{id}", "42")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(animalsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":\"42\",\"statusAnimal\":\"AVAILABLE\",\"animalSex\":\"FEMALE\",\"animalName\":\"Animal Name\",\"animalAge\":"
                                        + "\"YOUNG\",\"animalSpecie\":\"DOG\",\"animalDescription\":\"Animal Description\",\"animalIsCastrated\":false,"
                                        + "\"animalIsVaccinated\":false,\"photo\":\"QVhBWEFYQVg=\"}"));
    }

    /**
     * Method under test: {@link AnimalsController#getAllAnimals()}
     */
    @Test
    void testGetAllAnimals() throws Exception {
        // Arrange
        when(animalService.getAllAnimals()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/animal/getAll");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(animalsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link AnimalsController#gettingAvaliableAnimals()}
     */
    @Test
    void testGettingAvaliableAnimals() throws Exception {
        // Arrange
        when(animalService.getAvailableAnimals()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/animal/getAvailable");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(animalsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}
