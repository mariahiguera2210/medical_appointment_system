package sistemadereservas.practica.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import sistemadereservas.practica.application.lasting.ERole;
import sistemadereservas.practica.application.service.AuthenticationService;
import sistemadereservas.practica.domain.dto.AuthenticationDto;
import sistemadereservas.practica.domain.dto.UserDto;

import java.util.Set;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AuthenticationControllerTest {

    @Autowired
    private AuthenticationController authenticationController;
    @MockBean
    private AuthenticationService authenticationService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createUser_success() throws Exception {
        String uniqueEmail = "jane" + System.currentTimeMillis() + "@example.com";
        UserDto userDto = new UserDto(
                null, "Jane", uniqueEmail, "password123",true,
                ERole.USER
        );
        String userDtoJson = objectMapper.writeValueAsString(userDto);

        mockMvc.perform(post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userDtoJson))
                .andExpect(status().isCreated());
    }

    @Test
    void authenticate_success() throws Exception {
        AuthenticationDto authenticationDto = new AuthenticationDto("john.doe@example.com", "password");
        String token = "some-jwt-token";
        when(authenticationService.authenticate(any(AuthenticationDto.class))).thenReturn(token);

        String authenticationDtoJson = objectMapper.writeValueAsString(authenticationDto);

        mockMvc.perform(post("/api/v1/auth/authenticate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authenticationDtoJson))
                .andExpect(status().isOk())
                .andExpect(content().string(token));

        verify(authenticationService, times(1)).authenticate(any(AuthenticationDto.class));
    }


}