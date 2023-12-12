package sistemadereservas.practica.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record DoctorDto(

         Integer id,
         String name) {
}
