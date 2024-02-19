package sistemadereservas.practica.application.mapper;

import org.mapstruct.Mapper;
import sistemadereservas.practica.application.mapper.base.IBaseMapper;
import sistemadereservas.practica.domain.dto.AppointmentDto;
import sistemadereservas.practica.domain.entity.Appointment;

import java.util.List;

@Mapper
public interface AppointmentMapper extends IBaseMapper {
    Appointment toEntity(AppointmentDto dto);
    AppointmentDto toDto(Appointment entity);
    List<Appointment> toEntityList(List<AppointmentDto> dtoList);
    List<AppointmentDto> toDtoList(List<Appointment> entityList);
}
