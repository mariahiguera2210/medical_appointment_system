package sistemadereservas.practica.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import sistemadereservas.practica.application.lasting.ERole;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "\"user\"", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})

     public class User implements UserDetails {

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

    //2. Creo el rol Enum
    @Enumerated(EnumType.ORDINAL)
    private ERole role;

    @OneToMany
    @ToString.Exclude
    private List<Appointment> appointmentList;

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.email;
    }
    //4. poner return this.email, el campo con el que se va a hacer el login

    @Override
    public String getPassword() {
        return this.password;
    }
    //se agrega getPassword de la interface, de userDetales

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    //5. cambiar return de false a true, para indicar que nada ha expirado

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    //6. cambiar return de false a true

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    //7. cambiar return de false a true

    @Override
    public boolean isEnabled() {
        return this.enable;
    }
    //8. cambiar return de false a this.enable, que valide con el atributo si el usuario esta activo o no
}
