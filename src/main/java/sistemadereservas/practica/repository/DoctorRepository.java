package sistemadereservas.practica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sistemadereservas.practica.domain.entity.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository <Doctor, Integer>{

}
