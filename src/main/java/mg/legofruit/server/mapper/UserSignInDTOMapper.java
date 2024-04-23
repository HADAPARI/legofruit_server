package mg.legofruit.server.mapper;
import lombok.AllArgsConstructor;
import mg.legofruit.server.entity.Users;
import mg.legofruit.server.dto.UserSingInDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
@AllArgsConstructor
public class UserSignInDTOMapper implements Function<UserSingInDTO, Users> {

    @Override
    public Users apply(UserSingInDTO userSignInDTO) {
        Users user = new Users();
        user.setEmail(userSignInDTO.getEmail());
        user.setPassword(userSignInDTO.getPassword());
        return user;
    }
}
