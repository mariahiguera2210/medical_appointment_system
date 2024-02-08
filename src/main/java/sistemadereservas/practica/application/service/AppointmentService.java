package sistemadereservas.practica.application.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import sistemadereservas.practica.application.exception.BookingAppointsExceptions;
import sistemadereservas.practica.application.lasting.EMessage;
import sistemadereservas.practica.application.mapper.AppointmentMapper;
import sistemadereservas.practica.domain.dto.AppointmentDto;
import sistemadereservas.practica.domain.entity.Appointment;
import sistemadereservas.practica.domain.repository.AppointmentRepository;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Service
public record AppointmentService(
        AppointmentRepository appointmentRepository, AppointmentMapper mapper,
        UserService userService,
        DoctorService doctorService
) {

    public void createAppointment(AppointmentDto appointmentDto) {
        Appointment appointment = mapper.toEntity(appointmentDto);
        appointmentRepository.save(appointment);
    }

    public List<AppointmentDto> getAllAppointments(Integer offset, Integer limit)
            throws BookingAppointsExceptions {
        Pageable pageable = PageRequest.of(offset, limit);
        Page<Appointment> booking = appointmentRepository.findAll(pageable);
        if (booking.getContent().isEmpty()) {
            throw new BookingAppointsExceptions(EMessage.DATA_NOT_FOUND);
        }
        return mapper.toDtoList(booking.getContent());
    }

    public AppointmentDto findAppointmentById(Integer id) throws BookingAppointsExceptions {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new BookingAppointsExceptions(EMessage.DATA_NOT_FOUND));
        return mapper.toDto(appointment);
    }


    public void updateAppointment(Integer id, AppointmentDto appointmentDto) throws BookingAppointsExceptions {
        appointmentRepository.findById(id)
                    .orElseThrow(() -> new BookingAppointsExceptions(EMessage.DATA_NOT_FOUND));
        Appointment appointment = mapper.toEntity(appointmentDto);
        appointmentRepository.save(appointment);
    }

    public void removeAppointment(Integer id) throws BookingAppointsExceptions {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(()-> new BookingAppointsExceptions(EMessage.DATA_NOT_FOUND));
        appointmentRepository.delete(appointment);
    }


}
