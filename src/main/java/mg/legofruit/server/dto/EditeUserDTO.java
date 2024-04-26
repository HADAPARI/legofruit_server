package mg.legofruit.server.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mg.legofruit.server.entity.Country;
import mg.legofruit.server.entity.Region;

@Data
@NoArgsConstructor
public class EditeUserDTO {
    @NotBlank
    @Size(min = 3, max = 100)
    private String firstname;
    @NotBlank
    @Size(min = 3, max = 100)
    private String lastname;
    @Email
    private String email;
    @NotBlank
    @Size(min = 9, max = 20)
    private String phone;
    @NotBlank
    @Size(min = 3, max = 255)
    private String address;
    @NotNull
    private Integer region;
    @NotNull
    private Integer country;

}
