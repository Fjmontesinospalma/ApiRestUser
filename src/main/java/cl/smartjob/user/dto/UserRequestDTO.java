package cl.smartjob.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserRequestDTO {
    private String name;

    @Email(message = "Formato de correo inválido.")
    @Pattern(regexp = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$", message = "El formato del correo debe ser aaaaaaa@dominio.cl")
    private String email;

    @NotBlank(message = "La clave no puede estar vacía.")
    private String password;

    private List<PhoneDTO> phones = new ArrayList<>();


}
