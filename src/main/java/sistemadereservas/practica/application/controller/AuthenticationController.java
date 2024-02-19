package sistemadereservas.practica.application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sistemadereservas.practica.application.service.AuthenticationService;
import sistemadereservas.practica.domain.dto.AuthenticationDto;
import sistemadereservas.practica.domain.dto.UserDto;

@RestController
@RequestMapping("/api/v1/auth")
public record AuthenticationController(
        AuthenticationService authenticationService
) {
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDto userDto) {
        String token = authenticationService.register(userDto);
        return new ResponseEntity<>(token, HttpStatus.CREATED);
    }
    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationDto authenticationDto){
        String token = authenticationService.authenticate(authenticationDto);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
