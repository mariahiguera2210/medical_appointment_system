package sistemadereservas.practica.domain.dto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record AppointmentDto(
        Integer id,
        LocalDateTime appointmentDate,
        String reasonForVisit,
        boolean confirmed
) {


}
