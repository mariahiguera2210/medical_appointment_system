package sistemadereservas.practica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sistemadereservas.practica.domain.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {



}
