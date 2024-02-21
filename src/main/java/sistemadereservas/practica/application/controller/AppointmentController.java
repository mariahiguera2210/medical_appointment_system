package sistemadereservas.practica.application.controller;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistemadereservas.practica.application.exception.BookingAppointsExceptions;
import sistemadereservas.practica.application.service.AppointmentService;
import sistemadereservas.practica.domain.dto.AppointmentDto;
import java.util.List;

@RestController
@RequestMapping("/api/v1/appointment")
public record AppointmentController(
       AppointmentService appointmentService
) {
    @PostMapping("/create")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> createAppointment(@RequestBody AppointmentDto appointmentDto){
        appointmentService.createAppointment(appointmentDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/list/{offset}/{limit}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> getAllAppointments(
            @PathVariable("offset") Integer offset,
            @PathVariable("limit") Integer limit) throws BookingAppointsExceptions{

            List<AppointmentDto> appointments = appointmentService.getAllAppointments(offset, limit);
            return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @GetMapping("/search/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> findAppointmentById(
            @PathVariable("id") Integer id) throws BookingAppointsExceptions {
        AppointmentDto appointment = appointmentService.findAppointmentById(id);
        return new ResponseEntity<>(appointment, HttpStatus.FOUND);
    }

    @PutMapping("/edit/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> updateAppointment(
            @PathVariable("id") Integer id,
            @RequestBody AppointmentDto appointmentDto) throws BookingAppointsExceptions{
        appointmentService.updateAppointment(id, appointmentDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/delete/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> removeAppointment(@PathVariable("id") Integer id)
            throws BookingAppointsExceptions {
        appointmentService.removeAppointment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
