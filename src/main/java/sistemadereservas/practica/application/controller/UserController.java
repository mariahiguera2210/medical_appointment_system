package sistemadereservas.practica.application.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistemadereservas.practica.application.exception.BookingAppointsExceptions;
import sistemadereservas.practica.application.service.UserService;
import sistemadereservas.practica.domain.dto.UserDto;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/user")
public record UserController(
        UserService userService
) {

    @PostMapping
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto) {
        userService.registerUser(userDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{offset}/{limit}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> findAllUser(
            @PathVariable("offset") Integer offset,
            @PathVariable("limit") Integer limit) throws BookingAppointsExceptions {
        List<UserDto> users = userService.findAllUser(offset, limit);
        return new ResponseEntity<>(users, HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> findUserById(@PathVariable("id") Integer id) throws BookingAppointsExceptions {
        UserDto user = userService.findUserById(id);
        return new ResponseEntity<>(user, HttpStatus.FOUND);
    }
    @PutMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> updateUser(@PathVariable("id") Integer id, @RequestBody UserDto userDto)
            throws BookingAppointsExceptions {
        userService.updateUser(id, userDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> removeUser(@PathVariable("id") Integer id) throws BookingAppointsExceptions {
        userService.removeUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
