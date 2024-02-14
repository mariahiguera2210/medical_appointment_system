package sistemadereservas.practica.application.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import sistemadereservas.practica.application.exception.BookingAppointsExceptions;
import sistemadereservas.practica.application.lasting.ERole;
import sistemadereservas.practica.application.mapper.UserMapper;
import sistemadereservas.practica.domain.dto.UserDto;
import sistemadereservas.practica.domain.entity.User;
import sistemadereservas.practica.domain.repository.UserRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper mapper;



    @Test
    void registerUserTest() {
        //given

        UserDto userDto = new UserDto(
                null,
                "Jane",
                "jane.doe@example.com",
                "password123",
                true,
                ERole.USER // role
        );

        User user = new User(
                null,
                "Jane",
                "jane.doe@example.com",
                "password123",
                true,
                ERole.USER,
                null
        );

        when(mapper.toEntity(userDto)).thenReturn(user);
        userService.registerUser(userDto);
        verify(userRepository).save(user);

    }

    @Test
    void findAllUsersTest() throws BookingAppointsExceptions {
        Integer offset = 1;
        Integer limit = 5;
        User jane = new User(
                1,
                "Jane",
                "jane.doe@example.com",
                "password123",
                true,
                ERole.USER,
                null
        );

        User maria = new User(2,
                "Maria",
                "maria@example.com",
                "password123",
                true,
                ERole.USER,
                null);

        List<User> users = Arrays.asList(jane, maria);
        List<UserDto> expectedDtoList = Arrays.asList(
                mapper.toDto(jane), mapper.toDto(maria)
        );

        when(userRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(users));
        when(mapper.toDtoList(users)).thenReturn(expectedDtoList);
        List<UserDto> result = userService.findAllUser(offset,limit);

        assertEquals(expectedDtoList, result);
    }

    @Test
    void findUserByIdTest() throws BookingAppointsExceptions {
        Integer id = 1;
        UserDto expectedDto = new UserDto(
                1,
                "Jane",
                "jane.doe@example.com",
                "password123",
                true,
                ERole.USER
        );

        User user = User.builder()
                .id(1)
                .name("Jane")
                .email("jane.doe@example.com")
                .password("password123")
                .enable(true)
                .role(ERole.USER)
                .build();

        when(mapper.toDto(user)).thenReturn(expectedDto);
        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        UserDto userResult = userService.findUserById(id);

        assertEquals(expectedDto, userResult);
    }

    @Test
    void testFindUserByIdNotReturnData() {
        final Integer id = 1;
        when(userRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(BookingAppointsExceptions.class, () -> userService.findUserById(id));
    }

    @Test
    void updateUserTest() throws BookingAppointsExceptions {
        Integer id = 1;
        UserDto userDto = new UserDto(
                1,
                "Janeth",
                "janeth.doe@example.com",
                "password123",
                true,
                ERole.USER

        );

        User userDataBase = User.builder()
                .id(1)
                .name("Jane")
                .email("jane.doe@example.com")
                .password("password123")
                .enable(true)
                .role(ERole.USER)
                .build();

        User user = User.builder()
                .id(1)
                .name("Janeth")
                .email("janeth.doe@example.com")
                .password("password123")
                .enable(true)
                .role(ERole.USER)
                .build();

        when(mapper.toEntity(userDto)).thenReturn(user);
        when(userRepository.findById(id)).thenReturn(Optional.of(userDataBase));
        userService.updateUser(id, userDto);
    }


    @Test
    void removeUser() throws BookingAppointsExceptions {
        Integer id =1;
        User userFound = User.builder()
                .id(1)
                .name("Jane")
                .email("jane.doe@example.com")
                .password("password123")
                .enable(true)
                .role(ERole.USER)
                .build();
        when(userRepository.findById(id)).thenReturn(Optional.of(userFound));
        userService.removeUser(id);
        verify(userRepository).delete(userFound);

    }

    @Test
    void testRemoveDoctorNotReturnData() {
        // Given
        final Integer id = 50;

        // When
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        // Then
        assertThrows(BookingAppointsExceptions.class, () -> userService.removeUser(id));
    }


}