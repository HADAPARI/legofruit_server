package mg.legofruit.server.service;

import lombok.AllArgsConstructor;
import mg.legofruit.server.dto.UserSingInDTO;
import mg.legofruit.server.entity.Users;
import mg.legofruit.server.mapper.UserSignInDTOMapper;
import mg.legofruit.server.mapper.UserSignUpDTOMapper;
import mg.legofruit.server.repository.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserSignUpDTOMapper userSignUpDTOMapper;
    private final UserSignInDTOMapper userSignInDTOMapper;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserDetails signin(UserSingInDTO userSignInDTO){
        Optional<Users> userOptional = userRepository.findByEmail(userSignInDTO.getEmail());
        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("Invalid data signin");
        }

        Users user = userOptional.get();

        if (!bCryptPasswordEncoder.matches(userSignInDTO.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }

        UserDetails userDetails = User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .build();

        return userDetails;
    }
}
