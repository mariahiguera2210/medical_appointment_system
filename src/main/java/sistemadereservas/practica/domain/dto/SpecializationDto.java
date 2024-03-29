package sistemadereservas.practica.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import sistemadereservas.practica.domain.entity.Doctor;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record SpecializationDto(
        Integer id,
        String name
) {
}
