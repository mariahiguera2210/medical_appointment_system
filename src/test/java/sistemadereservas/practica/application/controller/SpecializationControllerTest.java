package sistemadereservas.practica.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import sistemadereservas.practica.application.service.SpecializationService;
import sistemadereservas.practica.domain.dto.SpecializationDto;
import org.springframework.http.MediaType;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
class SpecializationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SpecializationService specializationService;

    @Autowired
    private ObjectMapper objectMapper;

    private SpecializationDto specializationDto;

    @BeforeEach
    void setUp() {
        specializationDto = new SpecializationDto(1, "Cardiology");
    }

    @Test
    void createSpecialization() throws Exception {
        mockMvc.perform(post("/api/v1/specialization/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(specializationDto)))
                .andExpect(status().isCreated());
    }

    @Test
    void findSpecializationById() throws Exception {
        given(specializationService.findSpecializationById(1)).willReturn(specializationDto);

        mockMvc.perform(get("/api/v1/specialization/search/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.id").value(specializationDto.id()));
    }

    @Test
    void updateSpecialization() throws Exception {
        mockMvc.perform(put("/api/v1/specialization/edit/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(specializationDto)))
                .andExpect(status().isNoContent());
    }

    @Test
    void removeSpecialization() throws Exception {
        mockMvc.perform(delete("/api/v1/specialization/delete/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }




}