package sistemadereservas.practica.application.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import sistemadereservas.practica.application.exception.BookingAppointsExceptions;
import sistemadereservas.practica.application.lasting.EMessage;
import sistemadereservas.practica.application.mapper.DoctorMapper;
import sistemadereservas.practica.domain.dto.DoctorDto;
import sistemadereservas.practica.domain.entity.Doctor;
import sistemadereservas.practica.domain.repository.DoctorRepository;
import java.util.List;


@Service
public record DoctorService(
 DoctorRepository doctorRepository,
 DoctorMapper mapper
) {

    public void createDoctor(DoctorDto doctorDto) {
       Doctor doctor = mapper.toEntity(doctorDto);
       doctorRepository.save(doctor);
    }

    public List<DoctorDto> findAllDoctors(Integer offset, Integer limit)throws BookingAppointsExceptions{
        Pageable pageable = PageRequest.of(offset, limit);
        Page<Doctor> doctors = doctorRepository.findAll(pageable);
        if(doctors.getContent().isEmpty()){
            throw new BookingAppointsExceptions(EMessage.DATA_NOT_FOUND);
        }
        return mapper.toDtoList(doctors.getContent());
    }

    public DoctorDto findDoctorById(Integer id) throws BookingAppointsExceptions {
        Doctor doctor= doctorRepository.findById(id)
                .orElseThrow(() -> new BookingAppointsExceptions(EMessage.DATA_NOT_FOUND));
        return mapper.toDto(doctor);
    }


    public void updateDoctor(Integer id, DoctorDto doctorDto) throws BookingAppointsExceptions{
       doctorRepository.findById(id)
               .orElseThrow(()-> new BookingAppointsExceptions(EMessage.DATA_NOT_FOUND));
       Doctor doctor = mapper.toEntity(doctorDto);
       doctorRepository.save(doctor);
    }

    public void removeDoctor(Integer id) throws BookingAppointsExceptions{
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(EMessage.DOCTOR_NOT_FOUND.getMessage()));
        doctorRepository.delete(doctor);
    }

}
