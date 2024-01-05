package sistemadereservas.practica.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import sistemadereservas.practica.application.lasting.ERole;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserDto(
        Integer id,
        String name,
        String email,
        @JsonIgnore(value = false)
        String password,
        Boolean enable,
        ERole role
) {
}
