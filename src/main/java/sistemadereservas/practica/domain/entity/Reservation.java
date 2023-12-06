package sistemadereservas.practica.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name="reservation")
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private String customerName;
    private Integer customerId;
    private String doctorName;
    private LocalDateTime appointmentDateTime;
    private String reasonForVisit;
    private boolean confirmed;



}
