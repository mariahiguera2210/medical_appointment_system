package sistemadereservas.practica.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name =  "\"user\"")
public class User {

    @Id
    @SequenceGenerator(
            name = "user_id_sequence",
            sequenceName = "user_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_id_sequence"
    )

    private Integer id;
    private String name;
    private String email;
    private String password;
    private Boolean enable;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(email, user.email)
                && Objects.equals(password, user.password) && Objects.equals(enable, user.enable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password, enable);
    }
}
