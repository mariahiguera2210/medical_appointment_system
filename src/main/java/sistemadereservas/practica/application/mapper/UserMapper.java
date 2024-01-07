package sistemadereservas.practica.application.mapper;

import org.mapstruct.Mapper;
import sistemadereservas.practica.application.mapper.base.IBaseMapper;
import sistemadereservas.practica.domain.dto.UserDto;
import sistemadereservas.practica.domain.entity.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper extends IBaseMapper {
    User toEntity(UserDto dto);
    UserDto toDto(User entity);
    List<User> toEntityList(List<UserDto> dtoList);
    List<UserDto> toDtoList(List<User> entityList);
}
