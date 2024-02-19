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
import sistemadereservas.practica.application.service.AppointmentService;
import sistemadereservas.practica.domain.dto.AppointmentDto;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
class AppointmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppointmentService appointmentService;

    @Autowired
    private ObjectMapper objectMapper;

    private AppointmentDto appointmentDto;
    @BeforeEach
    void setUp() {
        appointmentDto = new AppointmentDto(1, LocalDateTime.now(), "Check-up", true, null, null);
    }

    @Test
    void createAppointment() throws Exception {
        mockMvc.perform(post("/api/v1/appointment/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(appointmentDto)))
                .andExpect(status().isCreated());
    }
    @Test
    void getAllAppointments() throws Exception {
        given(appointmentService.getAllAppointments(0, 10)).willReturn(Collections.singletonList(appointmentDto));

        mockMvc.perform(get("/api/v1/appointment/0/10")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$[0].id").value(appointmentDto.id()));
    }

    @Test
    void findAppointmentById() throws Exception {
        given(appointmentService.findAppointmentById(1)).willReturn(appointmentDto);

        mockMvc.perform(get("/api/v1/appointment/search/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.id").value(appointmentDto.id()));
    }

    @Test
    void updateAppointment() throws Exception {
        mockMvc.perform(put("/api/v1/appointment/edit/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(appointmentDto)))
                .andExpect(status().isNoContent());
    }

    @Test
    void removeAppointment() throws Exception {
        mockMvc.perform(delete("/api/v1/appointment/delete/1"))
                .andExpect(status().isNoContent());
    }


}