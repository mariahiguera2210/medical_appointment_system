package sistemadereservas.practica.application.service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import sistemadereservas.practica.application.exception.BookingAppointsExceptions;
import sistemadereservas.practica.application.lasting.ERole;
import sistemadereservas.practica.application.mapper.AppointmentMapper;
import sistemadereservas.practica.domain.dto.AppointmentDto;
import sistemadereservas.practica.domain.dto.DoctorDto;
import sistemadereservas.practica.domain.dto.UserDto;
import sistemadereservas.practica.domain.entity.Appointment;
import sistemadereservas.practica.domain.repository.AppointmentRepository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AppointmentServiceTest {
    @Mock
    private AppointmentRepository appointmentRepository;

    @Mock
    private AppointmentMapper mapper;

    @InjectMocks
    private AppointmentService appointmentService;

    private final UserDto userDto = new UserDto(
            1,
            "Maria",
            "maria@gmail.com",
            "maria123",
            true,
            ERole.USER,
            null
    );

    private final DoctorDto doctorDto = new DoctorDto(
            1,
            "Andres",
            "Castro",
            null,
            null
    );

    @Test
    void testCreateAppointment() {
        //Given -> configuracion inicial
        AppointmentDto appointmentDto = new AppointmentDto(
                null,
                LocalDateTime.of(2024, 2, 15, 14, 30),
                "pain",
                true,
                null,
                null
        );

        Appointment appointment = Appointment.builder()
                .appointmentDate(LocalDateTime.of(2024, 2, 15, 14, 30))
                .reasonForVisit("pain")
                .confirmed(true)
                .user(null)
                .user(null)
                .build();

        //when -> ejecucion de lo que vamos a probar
        when(mapper.toEntity(appointmentDto)).thenReturn(appointment);
        appointmentService.createAppointment(appointmentDto);
        //then -> verificacion de los resultados
        verify(appointmentRepository).save(appointment);

    }


    @Test
    void testGetAllAppointments() throws BookingAppointsExceptions {
        int offset = 0;
        int limit = 5;
        Appointment pain = new Appointment(
                2,
                LocalDateTime.of(2024, 2, 15, 14, 30),
                "pain",
                true,
                null,
                null);

        Appointment checkup = new Appointment(
                2,
                LocalDateTime.of(2024, 2, 15, 14, 30),
                "Checkup",
                true,
                null,
                null);

        List<Appointment> appointments = Arrays.asList(pain,checkup);
        List<AppointmentDto> expectedDtoList = Arrays.asList(
                mapper.toDto(pain), mapper.toDto(checkup)
        );

        //when
        when(appointmentRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(appointments));
        when(mapper.toDtoList(appointments)).thenReturn(expectedDtoList);
        List<AppointmentDto> result = appointmentService.getAllAppointments(offset, limit);

        //then
        assertEquals(expectedDtoList, result);
    }

    @Test
    void findAppointmentById() {
        final Integer id = 5;
        when(appointmentRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(BookingAppointsExceptions.class, () -> appointmentService.findAppointmentById(id));

    }

    @Test
    void testUpdateAppointment() throws BookingAppointsExceptions {
        final Integer id = 5;

        AppointmentDto appointmentDto = new AppointmentDto(
                5,
                LocalDateTime.of(2024, 2, 15, 14, 30),
                "pain",
                true,
                null,
                null);

        Appointment appointmentFound = Appointment.builder()
                .id(5)
                .appointmentDate(LocalDateTime.of(2024, 2, 3, 14, 30))
                .reasonForVisit("headache")
                .confirmed(true)
                .user(null)
                .doctor(null)
                .build();
        Appointment appointment = Appointment.builder()
                .id(5)
                .appointmentDate(LocalDateTime.of(2024, 2, 15, 14, 30))
                .reasonForVisit("pain")
                .confirmed(true)
                .user(null)
                .doctor(null)
                .build();
        when(mapper.toEntity(appointmentDto)).thenReturn(appointment);
        when(appointmentRepository.findById(id)).thenReturn(Optional.of(appointmentFound));
        appointmentService.updateAppointment(id, appointmentDto);
    }

    @Test
    void removeAppointment() {
    }
}