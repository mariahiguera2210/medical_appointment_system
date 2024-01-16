package sistemadereservas.practica.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="doctor")
public class Doctor {
    @Id
    @SequenceGenerator(
            name = "doc_id_sequence",
            sequenceName = "doc_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "doc_id_sequence"
    )

    private Integer id;
    private String name;

    @OneToMany
    @ToString.Exclude
    private List<Appointment> appointmentList;

    @OneToOne
    @JoinColumn(name = "id_specialization")
    private Specialization specialization;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return Objects.equals(id, doctor.id) && Objects.equals(name, doctor.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
