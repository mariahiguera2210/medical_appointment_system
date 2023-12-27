package sistemadereservas.practica.application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sistemadereservas.practica.application.service.DoctorService;
import sistemadereservas.practica.domain.dto.DoctorDto;

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
}
