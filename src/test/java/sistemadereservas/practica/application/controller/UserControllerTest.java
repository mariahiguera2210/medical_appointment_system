package sistemadereservas.practica.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import sistemadereservas.practica.application.lasting.ERole;
import sistemadereservas.practica.application.service.UserService;
import sistemadereservas.practica.domain.dto.UserDto;

import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    private UserDto userDto;

    @BeforeEach
    void setUp() {
        userDto = new UserDto(1, "John Doe", "john.doe@example.com", "password", true, ERole.USER);
    }

    @Test
    void registerUser() throws Exception {
        mockMvc.perform(post("/api/v1/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isCreated());
    }

    @Test
    void findAllUser() throws Exception {
        List<UserDto> users = Collections.singletonList(userDto);
        given(userService.findAllUser(0, 10)).willReturn(users);

        mockMvc.perform(get("/api/v1/user/find/0/10")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$[0].id").value(userDto.id()));
    }

    @Test
    void findUserById() throws Exception {
        given(userService.findUserById(1)).willReturn(userDto);

        mockMvc.perform(get("/api/v1/user/search/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.id").value(userDto.id()));
    }

    @Test
    void updateUser() throws Exception {
        mockMvc.perform(put("/api/v1/user/edit/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isNoContent());
    }

    @Test
    void removeUser() throws Exception {
        mockMvc.perform(delete("/api/v1/user/1"))
                .andExpect(status().isNoContent());
    }


}