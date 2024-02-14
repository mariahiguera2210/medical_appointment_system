package sistemadereservas.practica.application.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sistemadereservas.practica.application.exception.BookingAppointsExceptions;
import sistemadereservas.practica.application.lasting.EMessage;
import sistemadereservas.practica.application.mapper.UserMapper;
import sistemadereservas.practica.domain.dto.UserDto;
import sistemadereservas.practica.domain.entity.User;
import sistemadereservas.practica.domain.repository.UserRepository;

import java.util.List;

@Service
public record UserService(
        UserRepository userRepository,
        UserMapper mapper
) {
    public void registerUser(UserDto userDto){
        User user = mapper.toEntity(userDto);
        userRepository.save(user);
    }

    public List<UserDto> findAllUser(Integer offset, Integer limit) throws BookingAppointsExceptions{
        Pageable pageable = PageRequest.of(offset, limit);
        Page<User> users = userRepository.findAll(pageable);
        if (users.getContent().isEmpty()) {
            throw new BookingAppointsExceptions(EMessage.DATA_NOT_FOUND);
        }
        return mapper.toDtoList(users.getContent());
    }

    public UserDto findUserById(Integer id) throws BookingAppointsExceptions {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new BookingAppointsExceptions(EMessage.DATA_NOT_FOUND));
        return mapper.toDto(user);
    }

    public void updateUser(Integer id, UserDto userDto) throws BookingAppointsExceptions{
        userRepository.findById(id)
                .orElseThrow(() -> new BookingAppointsExceptions(EMessage.DATA_NOT_FOUND));
        User user = mapper.toEntity(userDto);
        userRepository.save(user);
    }

    public void removeUser(Integer id) throws BookingAppointsExceptions {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new BookingAppointsExceptions(EMessage.DATA_NOT_FOUND));
        userRepository.delete(user);
    }

}
