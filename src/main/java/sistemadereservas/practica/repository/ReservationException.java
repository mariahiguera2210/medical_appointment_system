package sistemadereservas.practica.repository;

import org.springframework.http.HttpStatus;
import sistemadereservas.practica.application.lasting.EMessage;

public class ReservationException extends Exception{

    private HttpStatus status;
    private String message;
    public ReservationException(EMessage message){
        this.status = message.getStatus();
        this.message = message.getMessage();
    }
}
