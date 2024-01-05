package sistemadereservas.practica.application.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sistemadereservas.practica.application.lasting.ERole;
import sistemadereservas.practica.domain.dto.AuthenticationDto;
import sistemadereservas.practica.domain.dto.UserDto;
import sistemadereservas.practica.domain.entity.User;
import sistemadereservas.practica.repository.UserRepository;

@Service
public record AuthenticationService(
        UserRepository userRepository,
        PasswordEncoder passwordEncoder,
        JwtService jwtService,
        AuthenticationManager authenticationManager
) {
    public String register(UserDto userDto) {
        User user = User.builder()
                .name(userDto.name())
                .email(userDto.email())
                .password(passwordEncoder.encode(userDto.password()))
                .role(ERole.USER)
                .enable(true)
                .build();
        userRepository.save(user);
        return jwtService.generateToken(user);
    }

    public String authenticate(AuthenticationDto authenticationDto){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationDto.email(),
                        authenticationDto.password()
                )
        );
        User user = userRepository.findUserByEmail(authenticationDto.email()).orElseThrow();
        return jwtService.generateToken(user);
    }

}
