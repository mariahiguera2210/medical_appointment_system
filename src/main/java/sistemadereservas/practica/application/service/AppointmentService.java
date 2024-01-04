package sistemadereservas.practica.application.service;
import org.springframework.stereotype.Service;
import sistemadereservas.practica.application.lasting.EMessage;
import sistemadereservas.practica.domain.dto.AppointmentDto;
import sistemadereservas.practica.domain.entity.Appointment;
import sistemadereservas.practica.repository.AppointmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public record AppointmentService(
        AppointmentRepository appointmentRepository
) {

    public void createAppointment(AppointmentDto appointmentDto) {
        Appointment appointment = Appointment.builder()
                .appointmentDate(appointmentDto.appointmentDate())
                .reasonForVisit(appointmentDto.reasonForVisit())
                .confirmed(appointmentDto.confirmed())
                .build();
        appointmentRepository.save(appointment);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Optional<Appointment> findAppointmentById(Integer id) {
        return appointmentRepository.findById(id);
    }

    public void removeAppointment(Integer id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(EMessage.APPOINTMENT_NOT_FOUND.getMessage()));

        appointmentRepository.delete(appointment);
    }


    public void updateAppointmentData(Appointment appointment, AppointmentDto appointmentDto) {

        appointment.setAppointmentDate(appointmentDto.appointmentDate());
        appointment.setReasonForVisit(appointmentDto.reasonForVisit());
        appointment.setConfirmed(appointmentDto.confirmed());

    }

    public void updateAppointment(AppointmentDto appointmentDto) {
        Appointment appointment = appointmentRepository.findById(appointmentDto.id())
                .orElseThrow(() -> new RuntimeException(EMessage.APPOINTMENT_NOT_FOUND.getMessage()));
        updateAppointmentData(appointment, appointmentDto);
        appointmentRepository.save(appointment);
    }



}
