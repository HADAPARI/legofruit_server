package mg.legofruit.server.service;

import lombok.AllArgsConstructor;
import mg.legofruit.server.dto.UserSignUpDTO;
import mg.legofruit.server.entity.Users;
import mg.legofruit.server.mapper.UserSignUpDTOMapper;
import mg.legofruit.server.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserSignUpDTOMapper userSignUpDTOMapper;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void signup(UserSignUpDTO userSignUpDTO){
        Optional<Users> userOptional = userRepository.findByEmail(userSignUpDTO.getEmail());
        if (userOptional.isPresent()) {
            throw new UsernameNotFoundException("User not found with email: " + userSignUpDTO.getEmail());
        }

        Users user = userSignUpDTOMapper.apply(userSignUpDTO);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);

    }
}
