package sistemadereservas.practica.application.exception;

import org.springframework.http.HttpStatus;
import sistemadereservas.practica.application.message.EMessage;

public class ReservationException extends Exception{

    private HttpStatus status;
    private String message;
    public ReservationException(EMessage message){
        this.status = message.getStatus();
        this.message = message.getMessage();
    }
}
