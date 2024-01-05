package sistemadereservas.practica.application.service;

import org.springframework.stereotype.Service;
import sistemadereservas.practica.application.lasting.EMessage;
import sistemadereservas.practica.domain.dto.DoctorDto;
import sistemadereservas.practica.domain.entity.Doctor;
import sistemadereservas.practica.domain.repository.DoctorRepository;

import java.util.List;
import java.util.Optional;

@Service
public record DoctorService(
 DoctorRepository doctorRepository
) {

    public void createDoctor(DoctorDto doctorDto) {
       Doctor doctor = Doctor.builder().name(doctorDto.name()).build();
       doctorRepository.save(doctor);
    }

    public List<Doctor> findAllDoctors(){
        return doctorRepository.findAll();
    }

    public Optional<Doctor> findDoctorById(Integer id){
        return doctorRepository.findById(id);
    }

    public void removeDoctor(Integer id){
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(EMessage.DOCTOR_NOT_FOUND.getMessage()));

        doctorRepository.delete(doctor);


    }
    public void updateDoctorInfo(Doctor doctor, DoctorDto doctorDto){

        doctor.setName(doctorDto.name());
    }

    public void updateDoctor(DoctorDto doctorDto){
        Doctor doctor = doctorRepository.findById(doctorDto.id())
                .orElseThrow(()-> new RuntimeException(EMessage.DOCTOR_NOT_FOUND.getMessage()));

        updateDoctorInfo(doctor, doctorDto);
        doctorRepository.save(doctor);
    }

}
