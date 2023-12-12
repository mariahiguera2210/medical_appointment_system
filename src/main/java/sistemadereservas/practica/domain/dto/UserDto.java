package sistemadereservas.practica.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserDto(
        Integer id,
        String name,
        String email,
        String password,
        Boolean enable
) {
}
