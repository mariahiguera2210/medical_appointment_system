package sistemadereservas.practica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sistemadereservas.practica.domain.entity.User;

import java.util.Optional;

public interface UserRespository extends JpaRepository <User, Integer> {

    //9. Se genera el metodo optional, para buscar al usuario por email, no por id
    Optional<User> findUsersByEmail(String email);
}
//10. Se crea el authentication Filter, para procesar las peticiones que llegan al servidor