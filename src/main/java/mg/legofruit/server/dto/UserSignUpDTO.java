package mg.legofruit.server.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserSignUpDTO {
    @NotBlank
    @Size(min = 3, max = 100)
    private String firstName;
    @NotBlank
    @Size(min = 3, max = 100)
    private String lastName;
    @Email
    private String email;
    @NotBlank
    private String avatar;
    @NotBlank
    @Size(min = 9, max = 20)
    private String phone;
    @NotBlank
    @Size(min = 3, max = 255)
    private String address;
    @NotBlank
    private Integer region;
    @NotBlank
    private Integer country;
    @NotBlank
    @Size(min = 6, max = 100)
    private String password;
}
