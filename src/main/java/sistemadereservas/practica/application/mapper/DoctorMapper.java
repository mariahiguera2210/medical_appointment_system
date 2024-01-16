package sistemadereservas.practica.application.mapper;

import org.mapstruct.Mapper;
import sistemadereservas.practica.application.mapper.base.IBaseMapper;
import sistemadereservas.practica.domain.dto.DoctorDto;
import sistemadereservas.practica.domain.entity.Doctor;

import java.util.List;

@Mapper
public interface DoctorMapper extends IBaseMapper {
    Doctor toEntity(DoctorDto dto);
    DoctorDto toDto(Doctor entity);
    List<Doctor> toEntityList(List<DoctorDto> dtoList);
    List<DoctorDto> toDtoList(List<Doctor> entityList);
}
