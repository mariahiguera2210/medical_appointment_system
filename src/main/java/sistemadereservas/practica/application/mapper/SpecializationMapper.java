package sistemadereservas.practica.application.mapper;

import org.mapstruct.Mapper;
import sistemadereservas.practica.application.mapper.base.IBaseMapper;
import sistemadereservas.practica.domain.dto.SpecializationDto;
import sistemadereservas.practica.domain.entity.Specialization;

import java.util.List;
@Mapper(componentModel = "spring")
public interface SpecializationMapper extends IBaseMapper {
        Specialization toEntity(SpecializationDto dto); //SpecializationDto -> objeto Specialization
        SpecializationDto toDto(Specialization entity); //objeto Specialization -> obejto SpecializationDto
        List<Specialization> toEntityList(List<SpecializationDto> dtoList); // lista SpecializationDto -> lista Specialization
        List<SpecializationDto> toDtoList(List<Specialization> entityList); // lista Specialization -> lista SpecializationDto


}
