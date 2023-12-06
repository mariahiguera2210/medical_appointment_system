package sistemadereservas.practica.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sistemadereservas.practica.application.exception.ReservationException;
import sistemadereservas.practica.application.message.EMessage;
import sistemadereservas.practica.domain.entity.Reservation;
import sistemadereservas.practica.repository.ReservationRepository;

import java.util.List;
import java.util.Optional;

@Service
public record ReservationService(
        ReservationRepository reservationRepository
) {


    public void createReservation(Reservation reservation){
        reservationRepository.save(reservation);
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Optional<Reservation> getReservationById(Integer id) {
        return reservationRepository.findById(id);
    }

    public void removeReservation(Integer id){
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(()-> new RuntimeException(EMessage.RESERVATION_NOT_FOUND.getMessage()));

        reservationRepository.delete(reservation);
    }

    public void updateReservation(Reservation reservation){
        reservationRepository.save(reservation);
    }

}
