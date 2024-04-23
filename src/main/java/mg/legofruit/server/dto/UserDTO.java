package mg.legofruit.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
    private String firstname;
    private String lastname;
    private String email;
    private String avatar;
    private String phone;
    private String address;
    private String region;
    private String country;
    private String role;

}
