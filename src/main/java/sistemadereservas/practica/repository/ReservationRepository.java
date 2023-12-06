package sistemadereservas.practica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sistemadereservas.practica.domain.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

 //Reservation findById(Integer id)

}
