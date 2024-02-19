package sistemadereservas.practica.application.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import sistemadereservas.practica.application.lasting.EMessage;

@Getter
public class BookingAppointsExceptions extends Exception {

    private final HttpStatus status;
    private final String message;

    public BookingAppointsExceptions(EMessage eMessage){
        this.status = eMessage.getStatus();
        this.message = eMessage.getMessage();
    }
}
