package sistemadereservas.practica.application.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistemadereservas.practica.application.exception.BookingAppointsExceptions;
import sistemadereservas.practica.application.service.SpecializationService;
import sistemadereservas.practica.domain.dto.SpecializationDto;
import java.util.List;

@RestController
@RequestMapping("/api/v1/specialization")
public record SpecializationController(
        SpecializationService specializationService
) {

    @PostMapping("/create")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> createSpecialization(@RequestBody SpecializationDto specializationDto){
        specializationService.createSpecialization(specializationDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/{offset}/{limit}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> getAllSpecializations(
            @PathVariable("offset") Integer offset,
            @PathVariable("limit") Integer limit) throws BookingAppointsExceptions{
        List<SpecializationDto> specializations = specializationService.findAllSpecializations(offset, limit);
        return new ResponseEntity<>(specializations, HttpStatus.FOUND);
    }

    @GetMapping("search/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> findSpecializationById(@PathVariable("id") Integer id) throws BookingAppointsExceptions{
        SpecializationDto specialization = specializationService.findSpecializationById(id);
        return new ResponseEntity<>(specialization, HttpStatus.FOUND);
    }

    @PutMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> updateSpecialization(
            @PathVariable("id") Integer id,
            @RequestBody SpecializationDto specializationDto) throws BookingAppointsExceptions {
        specializationService.updateSpecialization(id, specializationDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @DeleteMapping("delete/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> removeSpecialization(@PathVariable("id") Integer id)
            throws BookingAppointsExceptions {
        specializationService.removeSpecialization(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
