package sistemadereservas.practica.application.service;

import org.springframework.stereotype.Service;
import sistemadereservas.practica.domain.dto.DoctorDto;
import sistemadereservas.practica.domain.entity.Doctor;
import sistemadereservas.practica.repository.DoctorRepository;

@Service
public record DoctorService(
 DoctorRepository doctorRepository
) {

    public void createDoctor(DoctorDto doctorDto) {
       Doctor doctor = Doctor.builder().name(doctorDto.name()).build();
       doctorRepository.save(doctor);
    }
}
