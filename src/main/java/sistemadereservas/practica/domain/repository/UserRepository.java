package sistemadereservas.practica.domain.repository;

import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import sistemadereservas.practica.domain.entity.User;

import java.awt.print.Pageable;
import java.util.Optional;

public interface UserRepository extends JpaRepository <User, Integer> {

    //9. Se genera el metodo optional, para buscar al usuario por email, no por id
    Optional<User> findUserByEmail(String email);
//    Page<User> findAll(Pageable pageable);
}
//10. Se crea el authentication Filter, para procesar las peticiones que llegan al servidor