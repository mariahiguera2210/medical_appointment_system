package sistemadereservas.practica.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="appointment")
public class Appointment {
    @Id
    @SequenceGenerator(
            name = "appt_id_sequence",
            sequenceName = "appt_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "appt_id_sequence"
    )

    private Integer id;
    private LocalDateTime appointmentDate;
    private String reasonForVisit;
    private boolean confirmed;

    @ManyToOne
    @JoinColumn(name= "id_user")
    private User user;
    @ManyToOne
    @JoinColumn(name= "id_doctor")
    private Doctor doctor;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return confirmed == that.confirmed && Objects.equals(id, that.id) && Objects.equals(appointmentDate, that.appointmentDate) && Objects.equals(reasonForVisit, that.reasonForVisit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, appointmentDate, reasonForVisit, confirmed);
    }
}
