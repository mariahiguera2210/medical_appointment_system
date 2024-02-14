package sistemadereservas.practica.application.service;

import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import sistemadereservas.practica.application.exception.BookingAppointsExceptions;
import sistemadereservas.practica.application.mapper.DoctorMapper;
import sistemadereservas.practica.domain.dto.DoctorDto;
import sistemadereservas.practica.domain.entity.Doctor;
import sistemadereservas.practica.domain.repository.DoctorRepository;

import java.util.Arrays;

import javax.print.Doc;

import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DoctorServiceTest {
    @Mock
    private DoctorRepository doctorRepository;
    @Mock
    private DoctorMapper mapper;
    @InjectMocks
    private DoctorService doctorService;


    @Test
    void testCreateDoctor() {
        DoctorDto doctorDto = new DoctorDto(
                null,
                "Kyle",
                "Rdgers",
                null

        );

        Doctor doctor = Doctor.builder()
                .name("Kyle")
                .lastName("Rodgers")
                .specialization(null)
                .build();

        when(mapper.toEntity(doctorDto)).thenReturn(doctor);
        doctorService.createDoctor(doctorDto);
        verify(doctorRepository).save(doctor);

    }

    @Test
    void testFindAllDoctors() throws BookingAppointsExceptions {
        Integer offset = 1;
        Integer limit = 5;

        Doctor rodgers = new Doctor(
                1,
                "Kyle",
                "Rodgers",
                null,
                null
        );

        Doctor johnson = new Doctor(
                2,
                "Emily",
                "Johson",
                null,
                null
        );

        List<Doctor> doctors = Arrays.asList(rodgers, johnson);
        List<DoctorDto> expectedDtoList = Arrays.asList(
                mapper.toDto(rodgers), mapper.toDto(johnson)
        );

        when(doctorRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(doctors));
        when(mapper.toDtoList(doctors)).thenReturn(expectedDtoList);
        List<DoctorDto> result = doctorService.findAllDoctors(offset, limit);

        assertEquals(expectedDtoList, result);

    }

    @Test
    void testFindDoctorById() throws BookingAppointsExceptions {
        Integer id = 1;
        DoctorDto expectedDto = new DoctorDto(
                1,
                "Kyle",
                "Rodgers",
                null
        );

        Doctor doctor = Doctor.builder()
                .id(1)
                .name("Kyle")
                .lastName("Rodgers")
                .specialization(null)
                .build();

        when(mapper.toDto(doctor)).thenReturn(expectedDto);
        when(doctorRepository.findById(id)).thenReturn(Optional.of(doctor));
        DoctorDto doctorResult = doctorService.findDoctorById(id);

        assertEquals(expectedDto, doctorResult);

    }

    @Test
    void testFindDoctorByIdNotReturnData() {
        final Integer id = 1;
        when(doctorRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(BookingAppointsExceptions.class, () -> doctorService.findDoctorById(id));
    }

    @Test
    void updateDoctor() {

    }

    @Test
    void removeDoctor() {
    }
}