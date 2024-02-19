package sistemadereservas.practica.application.controller;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistemadereservas.practica.application.exception.BookingAppointsExceptions;
import sistemadereservas.practica.application.service.DoctorService;
import sistemadereservas.practica.domain.dto.DoctorDto;
import java.util.List;

@RestController
@RequestMapping("/api/v1/doctor")
public record DoctorController(
        DoctorService doctorService
)
 {
     @PostMapping("/create")
     @SecurityRequirement(name = "bearerAuth")
     public ResponseEntity<?> createDoctor(@RequestBody DoctorDto doctorDto){
         doctorService.createDoctor(doctorDto);
         return new ResponseEntity<>(HttpStatus.CREATED);
     }

     @GetMapping("/{offset}/{limit}")
     @SecurityRequirement(name = "bearerAuth")
     public ResponseEntity<?> getAllDoctors(
             @PathVariable("offset") Integer offset,
             @PathVariable("limit") Integer limit) throws BookingAppointsExceptions {
         List<DoctorDto> doctors = doctorService.findAllDoctors(offset,limit);
         return new ResponseEntity<>(doctors, HttpStatus.OK);
     }

     @GetMapping("/search/{id}")
     @SecurityRequirement(name = "bearerAuth")
     public ResponseEntity<?> findDoctorById(
             @PathVariable("id") Integer id) throws BookingAppointsExceptions {
         DoctorDto doctor = doctorService.findDoctorById(id);
         return new ResponseEntity<>(doctor, HttpStatus.FOUND);
     }

     @PutMapping("/{id}")
     @SecurityRequirement(name = "bearerAuth")
     public ResponseEntity<?> updateDoctor(
             @PathVariable("id") Integer id,
             @RequestBody DoctorDto doctorDto) throws BookingAppointsExceptions{
         doctorService.updateDoctor(id, doctorDto);
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
     }

     @DeleteMapping("/delete/{id}")
     @SecurityRequirement(name = "bearerAuth")
     public ResponseEntity<?> deleteDoctorById(@PathVariable("id") Integer id)
             throws BookingAppointsExceptions {
         doctorService.removeDoctor(id);
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
     }

}
