package sistemadereservas.practica.application.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistemadereservas.practica.application.service.DoctorService;
import sistemadereservas.practica.domain.dto.DoctorDto;
import sistemadereservas.practica.repository.ReservationException;

@RestController
@RequestMapping("/api/v1/doctor")
public record DoctorController(
        DoctorService doctorService
)
 {
     @PostMapping("create")
     public ResponseEntity<?> createDoctor(@RequestBody DoctorDto doctorDto){
         doctorService.createDoctor(doctorDto);
         return new ResponseEntity<>(HttpStatus.CREATED);
     }

     @GetMapping("all")
     public ResponseEntity<?> getAllDoctors(){
         return new ResponseEntity<>(doctorService.findAllDoctors(), HttpStatus.OK);
     }

     @DeleteMapping("delete/{id}")
     public ResponseEntity<?> deleteDoctorById(@PathVariable("id") Integer id)
         throws ReservationException, ReservationException {
         doctorService.removeDoctor(id);
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
     }
     @PutMapping
     public ResponseEntity<?> updateDoctor(@RequestBody DoctorDto doctorDto){
         doctorService.updateDoctor(doctorDto);
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
     }


}
