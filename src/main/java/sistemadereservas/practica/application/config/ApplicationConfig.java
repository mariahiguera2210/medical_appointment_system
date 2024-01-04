package sistemadereservas.practica.application.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import sistemadereservas.practica.application.lasting.EMessage;
import sistemadereservas.practica.repository.UserRespository;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final UserRespository userRespository;


    @Bean //conexion entre userDetailService y la base de dtos
    public UserDetailsService userDetailsService(){
        return username -> userRespository.findUsersByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(EMessage.USER_NOT_FOUND.getMessage()));
    }
    //siguiente paso, crear la clase SecurityConfig, ahi crear el AuthenticationProvider

    @Bean //indicar de donde nos vamos a autenticar
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder()); //codificador de contraseñas
        return authenticationProvider;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean //objeto que ayude a administar la autenticacion
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }


}
