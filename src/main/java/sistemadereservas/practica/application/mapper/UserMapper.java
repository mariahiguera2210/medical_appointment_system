package sistemadereservas.practica.application.mapper;

import org.mapstruct.Mapper;
import sistemadereservas.practica.application.mapper.base.IBaseMapper;
import sistemadereservas.practica.domain.dto.UserDto;
import sistemadereservas.practica.domain.entity.User;

import java.util.List;

@Mapper
public interface UserMapper extends IBaseMapper {
    User toEntity(UserDto dto); //UserDto -> objeto User
    UserDto toDto(User entity); //objeto User -> obejto UserDto
    List<User> toEntityList(List<UserDto> dtoList); // lista UserDto -> lista User
    List<UserDto> toDtoList(List<User> entityList); // lista User -> lista UserDto
}


