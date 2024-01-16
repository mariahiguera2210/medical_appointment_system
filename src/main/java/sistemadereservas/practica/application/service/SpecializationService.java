package sistemadereservas.practica.application.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sistemadereservas.practica.application.exception.BookingAppointsExceptions;
import sistemadereservas.practica.application.lasting.EMessage;
import sistemadereservas.practica.application.mapper.SpecializationMapper;
import sistemadereservas.practica.domain.dto.SpecializationDto;
import sistemadereservas.practica.domain.entity.Specialization;
import sistemadereservas.practica.domain.repository.SpecializationRepository;

import java.util.List;

@Service
public record SpecializationService(
        SpecializationRepository specializationRepository,
        SpecializationMapper mapper
) {

    public void createSpecialization(SpecializationDto specializationDto){
        Specialization specialization = mapper.toEntity(specializationDto);
        specializationRepository.save(specialization);
    }

    public List<SpecializationDto> findAllSpecializations(Integer offset, Integer limit) throws BookingAppointsExceptions{
        Pageable pageable = PageRequest.of(offset, limit);
        Page<Specialization> specializations = specializationRepository.findAll(pageable);
        if(specializations.getContent().isEmpty()){
            throw new BookingAppointsExceptions(EMessage.DATA_NOT_FOUND);
        }
        return mapper.toDtoList(specializations.getContent());
    }

    public SpecializationDto findSpecializationById(Integer id) throws BookingAppointsExceptions{
        Specialization specialization = specializationRepository.findById(id)
                .orElseThrow(()-> new BookingAppointsExceptions(EMessage.DATA_NOT_FOUND));
        return mapper.toDto(specialization);
    }

    public void updateSpecialization(Integer id, SpecializationDto specializationDto) throws BookingAppointsExceptions {
        specializationRepository.findById(id)
                .orElseThrow(()->new BookingAppointsExceptions(EMessage.DATA_NOT_FOUND));
        Specialization specialization = mapper.toEntity(specializationDto);
        specializationRepository.save(specialization);
    }

    public void removeSpecialization(Integer id) throws BookingAppointsExceptions{
        Specialization specialization = specializationRepository.findById(id)
                .orElseThrow(()-> new BookingAppointsExceptions(EMessage.DATA_NOT_FOUND));
        specializationRepository.delete(specialization);
    }

}
