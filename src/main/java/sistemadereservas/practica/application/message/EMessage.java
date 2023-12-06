package sistemadereservas.practica.application.message;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum EMessage {
    RESERVATION_NOT_FOUND(HttpStatus.NOT_FOUND, "Reserva no encontrada");

    private final HttpStatus status;
    private final String message;
}
