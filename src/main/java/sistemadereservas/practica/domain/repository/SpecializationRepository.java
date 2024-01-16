package sistemadereservas.practica.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sistemadereservas.practica.domain.entity.Specialization;

public interface SpecializationRepository extends JpaRepository<Specialization, Integer> {
}
