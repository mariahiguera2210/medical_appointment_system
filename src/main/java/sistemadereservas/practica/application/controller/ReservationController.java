package sistemadereservas.practica.application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistemadereservas.practica.application.exception.ReservationException;
import sistemadereservas.practica.application.service.ReservationService;
import sistemadereservas.practica.domain.entity.Appointment;

@RestController
@RequestMapping("api/reservation")
public record ReservationController(
        ReservationService reservationService
) {

    @PostMapping("/reservation")
    public ResponseEntity<?> createReservation(@RequestBody Appointment appointment){
        reservationService.createReservation(appointment);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/allReservations")
    public ResponseEntity<?> showReservations(){
        return new ResponseEntity<>(reservationService.getAllReservations(),HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> removeReservation(@PathVariable("id") Integer id)
            throws ReservationException, ReservationException {
        reservationService.removeReservation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<?> updateReservation(@RequestBody Appointment appointment){
        reservationService.updateReservation(appointment);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
