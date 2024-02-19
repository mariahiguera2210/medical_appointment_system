package sistemadereservas.practica.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import sistemadereservas.practica.application.service.DoctorService;
import sistemadereservas.practica.domain.dto.DoctorDto;
import sistemadereservas.practica.domain.dto.SpecializationDto;

import java.util.Collections;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
class DoctorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DoctorService doctorService;

    @Autowired
    private ObjectMapper objectMapper;

    private DoctorDto doctorDto;

    @BeforeEach
    void setUp() {
        SpecializationDto specialization = new SpecializationDto(1, "Cardiology");
        doctorDto = new DoctorDto(1, "John", "Doe", specialization);
    }

    @Test
    void createDoctor() throws Exception {
        mockMvc.perform(post("/api/v1/doctor/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(doctorDto)))
                .andExpect(status().isCreated());
    }

    @Test
    void getAllDoctors() throws Exception {
        given(doctorService.findAllDoctors(0, 10)).willReturn(Collections.singletonList(doctorDto));

        mockMvc.perform(get("/api/v1/doctor/0/10")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(doctorDto.id()))
                .andExpect(jsonPath("$[0].name").value(doctorDto.name()))
                .andExpect(jsonPath("$[0].lastName").value(doctorDto.lastName()));
    }

    @Test
    void findDoctorById() throws Exception {
        given(doctorService.findDoctorById(1)).willReturn(doctorDto);

        mockMvc.perform(get("/api/v1/doctor/search/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.id").value(doctorDto.id()))
                .andExpect(jsonPath("$.name").value(doctorDto.name()))
                .andExpect(jsonPath("$.lastName").value(doctorDto.lastName()));
    }

    @Test
    void updateDoctor() throws Exception {
        mockMvc.perform(put("/api/v1/doctor/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(doctorDto)))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteDoctorById() throws Exception {
        mockMvc.perform(delete("/api/v1/doctor/delete/1"))
                .andExpect(status().isNoContent());
    }





}