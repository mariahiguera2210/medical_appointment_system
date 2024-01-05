package sistemadereservas.practica.application.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistemadereservas.practica.domain.repository.ReservationException;
import sistemadereservas.practica.application.service.AppointmentService;
import sistemadereservas.practica.domain.dto.AppointmentDto;

@RestController
@RequestMapping("/api/v1/appointment")
public record AppointmentController(
       AppointmentService appointmentService
) {
    @PostMapping("/create")
    public ResponseEntity<?> createAppointment(@RequestBody AppointmentDto appointmentDto){
        appointmentService.createAppointment(appointmentDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllAppointments(){
        return new ResponseEntity<>(appointmentService.getAllAppointments(),HttpStatus.OK);
    }

    @GetMapping("search/{id}")
    public ResponseEntity<?> getAppointmentById(@PathVariable("id") Integer id) {

        return new ResponseEntity<>(appointmentService.findAppointmentById(id), HttpStatus.OK);

    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> removeReservation(@PathVariable("id") Integer id)
            throws ReservationException, ReservationException {
        appointmentService.removeAppointment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<?> updateAppointment(@RequestBody AppointmentDto appointmentDto){
        appointmentService.updateAppointment(appointmentDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
