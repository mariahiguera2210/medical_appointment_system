package sistemadereservas.practica.application.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import sistemadereservas.practica.application.lasting.ERole;
import sistemadereservas.practica.domain.dto.AuthenticationDto;
import sistemadereservas.practica.domain.dto.UserDto;
import sistemadereservas.practica.domain.entity.User;
import sistemadereservas.practica.domain.repository.UserRepository;


import java.util.Optional;



import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthenticationService authenticationService;

    private final UserDto sampleUserDto = new UserDto(
            2, // id
            "Jane", // firstName
            "jane.doe@example.com", // email
            "password123", // password
            true, // enable
            ERole.USER// role
    );
    private final AuthenticationDto sampleAuthenticationDto = new AuthenticationDto(
            "jane.doe@example.com", "password123"
    );

    private final User sampleUser = new User(
            2, // id
            "Jane", // firstName
            "jane.doe@example.com", // email
            "password123", // password
            true, // enable
           ERole.USER, // role
            null
    );



    @Test
    void testRegister() {
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(sampleUser);
        when(jwtService.generateToken(any(User.class))).thenReturn("token");

        String result = authenticationService.register(sampleUserDto);

        assertNotNull(result);
        verify(userRepository, times(1)).save(any(User.class));
        verify(jwtService, times(1)).generateToken(any(User.class));
    }

    @Test
    void testAuthenticate() {

        when(userRepository.findUserByEmail(anyString())).thenReturn(Optional.of(sampleUser));
        when(jwtService.generateToken(any(User.class))).thenReturn("token");

        String result = authenticationService.authenticate(sampleAuthenticationDto);

        assertNotNull(result);
        verify(authenticationManager, times(1))
                .authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(jwtService, times(1)).generateToken(any(User.class));
    }
}