package mg.legofruit.server.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationDTO {
    @Email
    private String email;
    @Size(min = 6, max = 100)
    private String password;
}

