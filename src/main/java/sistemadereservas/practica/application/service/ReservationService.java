package sistemadereservas.practica.application.service;

import org.springframework.stereotype.Service;
import sistemadereservas.practica.application.message.EMessage;
import sistemadereservas.practica.domain.entity.Appointment;
import sistemadereservas.practica.repository.AppointmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public record ReservationService(
        AppointmentRepository appointmentRepository
) {


    public void createReservation(Appointment appointment){
        appointmentRepository.save(appointment);
    }

    public List<Appointment> getAllReservations() {
        return appointmentRepository.findAll();
    }

    public Optional<Appointment> getReservationById(Integer id) {
        return appointmentRepository.findById(id);
    }

    public void removeReservation(Integer id){
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(()-> new RuntimeException(EMessage.RESERVATION_NOT_FOUND.getMessage()));

        appointmentRepository.delete(appointment);
    }

    public void updateReservation(Appointment appointment){
        appointmentRepository.save(appointment);
    }

}
