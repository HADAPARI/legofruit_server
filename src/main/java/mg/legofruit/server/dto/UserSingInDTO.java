package mg.legofruit.server.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserSingInDTO {
    @Email
    private String email;
    @NotBlank
    @Size(min = 6, max = 100)
    private String password;


}
