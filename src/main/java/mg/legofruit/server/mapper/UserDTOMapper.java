package mg.legofruit.server.mapper;

import mg.legofruit.server.dto.UserDTO;
import mg.legofruit.server.entity.Users;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserDTOMapper implements Function<Users, UserDTO> {
    @Override
    public UserDTO apply(Users user) {
        return new UserDTO(
                 user.getFirstname(),
                user.getLastname(),
                user.getEmail(),
                user.getAvatar(),
                user.getPhone(),
                user.getAddress(),
                user.getRegion().getName(),
                user.getCountry().getName(),
                user.getRole().getType().name()
        );
    }
}
