package sistemadereservas.practica.application.lasting;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum EMessage {
    APPOINTMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "Appointment not found..."),
    DOCTOR_NOT_FOUND(HttpStatus.NOT_FOUND, "Doctor nor found"),

    DATA_NOT_FOUND(HttpStatus.NOT_FOUND, "The data was not found"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "The user was not found"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "The user was not Authorized to access at the application");



    private final HttpStatus status;
    private final String message;
}
