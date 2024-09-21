package br.ufcg.animais.animais_ufcg.diffblue_controllers.adoption_reports;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import br.ufcg.animais.animais_ufcg.controllers.adoption_reports.AdoptionReportsController;
import br.ufcg.animais.animais_ufcg.dtos.adoption_reports.AdoptionReportsPostPutRequestDTO;
import br.ufcg.animais.animais_ufcg.dtos.adoption_reports.AdoptionReportsResponseDTO;
import br.ufcg.animais.animais_ufcg.services.adoption_reports.AdoptionReportService;
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

@ContextConfiguration(classes = {AdoptionReportsController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class AdoptionReportsControllerDiffblueTest {
    @MockBean
    private AdoptionReportService adoptionReportService;

    @Autowired
    private AdoptionReportsController adoptionReportsController;

    /**
     * Method under test:
     * {@link AdoptionReportsController#creatingAdoptionReport(AdoptionReportsPostPutRequestDTO)}
     */
    @Test
    void testCreatingAdoptionReport() throws Exception {
        // Arrange
        AdoptionReportsResponseDTO.AdoptionReportsResponseDTOBuilder idResult = AdoptionReportsResponseDTO.builder()
                .adoptionReport("Adoption Report")
                .animalID("Animal ID")
                .animalOwnerName("Animal Owner Name")
                .id("42");
        AdoptionReportsResponseDTO buildResult = idResult.photo("AXAXAXAX".getBytes("UTF-8")).build();
        when(adoptionReportService.creatingAdoptionReport(Mockito.<AdoptionReportsPostPutRequestDTO>any()))
                .thenReturn(buildResult);

        AdoptionReportsPostPutRequestDTO adoptionReportsPostPutRequestDTO = new AdoptionReportsPostPutRequestDTO();
        adoptionReportsPostPutRequestDTO.setAdoptionReport("Adoption Report");
        adoptionReportsPostPutRequestDTO.setAnimalID("Animal ID");
        adoptionReportsPostPutRequestDTO.setAnimalOwnerName("Animal Owner Name");
        adoptionReportsPostPutRequestDTO.setId("42");
        adoptionReportsPostPutRequestDTO.setPhoto("AXAXAXAX".getBytes("UTF-8"));
        String content = (new ObjectMapper()).writeValueAsString(adoptionReportsPostPutRequestDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/adoption_report/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(adoptionReportsController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":\"42\",\"animalID\":\"Animal ID\",\"animalOwnerName\":\"Animal Owner Name\",\"adoptionReport\":\"Adoption"
                                        + " Report\",\"photo\":\"QVhBWEFYQVg=\"}"));
    }

    /**
     * Method under test:
     * {@link AdoptionReportsController#gettingAdoptionReport(String)}
     */
    @Test
    void testGettingAdoptionReport() throws Exception {
        // Arrange
        AdoptionReportsResponseDTO.AdoptionReportsResponseDTOBuilder idResult = AdoptionReportsResponseDTO.builder()
                .adoptionReport("Adoption Report")
                .animalID("Animal ID")
                .animalOwnerName("Animal Owner Name")
                .id("42");
        AdoptionReportsResponseDTO buildResult = idResult.photo("AXAXAXAX".getBytes("UTF-8")).build();
        when(adoptionReportService.gettingAdoptionReport(Mockito.<String>any())).thenReturn(buildResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/adoption_report/get/{id}", "42");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(adoptionReportsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":\"42\",\"animalID\":\"Animal ID\",\"animalOwnerName\":\"Animal Owner Name\",\"adoptionReport\":\"Adoption"
                                        + " Report\",\"photo\":\"QVhBWEFYQVg=\"}"));
    }

    /**
     * Method under test: {@link AdoptionReportsController#gettingAllReports()}
     */
    @Test
    void testGettingAllReports() throws Exception {
        // Arrange
        when(adoptionReportService.gettingAllReports()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/adoption_report/getAll");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(adoptionReportsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test:
     * {@link AdoptionReportsController#deleteAdoptionReport(String)}
     */
    @Test
    void testDeleteAdoptionReport() throws Exception {
        // Arrange
        doNothing().when(adoptionReportService).deleteAdoptionReport(Mockito.<String>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/adoption_report/{id}", "42");

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(adoptionReportsController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string(""));
    }

    /**
     * Method under test:
     * {@link AdoptionReportsController#deleteAdoptionReport(String)}
     */
    @Test
    void testDeleteAdoptionReport2() throws Exception {
        // Arrange
        doNothing().when(adoptionReportService).deleteAdoptionReport(Mockito.<String>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/adoption_report/{id}", "42");
        requestBuilder.contentType("https://example.org/example");

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(adoptionReportsController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string(""));
    }

    /**
     * Method under test:
     * {@link AdoptionReportsController#updateAdoptionReport(String, AdoptionReportsPostPutRequestDTO)}
     */
    @Test
    void testUpdateAdoptionReport() throws Exception {
        // Arrange
        AdoptionReportsResponseDTO.AdoptionReportsResponseDTOBuilder idResult = AdoptionReportsResponseDTO.builder()
                .adoptionReport("Adoption Report")
                .animalID("Animal ID")
                .animalOwnerName("Animal Owner Name")
                .id("42");
        AdoptionReportsResponseDTO buildResult = idResult.photo("AXAXAXAX".getBytes("UTF-8")).build();
        when(adoptionReportService.updateAdoptionReport(Mockito.<String>any(),
                Mockito.<AdoptionReportsPostPutRequestDTO>any())).thenReturn(buildResult);

        AdoptionReportsPostPutRequestDTO adoptionReportsPostPutRequestDTO = new AdoptionReportsPostPutRequestDTO();
        adoptionReportsPostPutRequestDTO.setAdoptionReport("Adoption Report");
        adoptionReportsPostPutRequestDTO.setAnimalID("Animal ID");
        adoptionReportsPostPutRequestDTO.setAnimalOwnerName("Animal Owner Name");
        adoptionReportsPostPutRequestDTO.setId("42");
        adoptionReportsPostPutRequestDTO.setPhoto("AXAXAXAX".getBytes("UTF-8"));
        String content = (new ObjectMapper()).writeValueAsString(adoptionReportsPostPutRequestDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/adoption_report/update/{id}", "42")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(adoptionReportsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":\"42\",\"animalID\":\"Animal ID\",\"animalOwnerName\":\"Animal Owner Name\",\"adoptionReport\":\"Adoption"
                                        + " Report\",\"photo\":\"QVhBWEFYQVg=\"}"));
    }
}
