package sistemadereservas.practica.application.lasting;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum EMessage {
    APPOINTMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "Appointment not found..."),
    DOCTOR_NOT_FOUND(HttpStatus.NOT_FOUND, "Doctor no encontrado");

    private final HttpStatus status;
    private final String message;
}
