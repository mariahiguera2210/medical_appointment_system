package sistemadereservas.practica.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;


import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record DoctorDto(
         Integer id,
         String name,
         String lastName,
         SpecializationDto specialization,
         List<AppointmentDto> appointmentList

) {
}
