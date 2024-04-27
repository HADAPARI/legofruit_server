package mg.legofruit.server.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mg.legofruit.server.dto.AuthenticationDTO;
import mg.legofruit.server.dto.RegisterDTO;
import mg.legofruit.server.dto.UserDTO;
import mg.legofruit.server.entity.Users;
import mg.legofruit.server.mapper.RegisterDTOMapper;
import mg.legofruit.server.mapper.UserDTOMapper;
import mg.legofruit.server.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {
    private UserRepository userRepository;
    private RegisterDTOMapper registerDTOMapper;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private MailService mailService;
    private JWTService jwtService;
    private AuthenticationManager authenticationManager;
    private UserDTOMapper userDTOMapper;

    public void signup(RegisterDTO registerDTO) {
        Optional<Users> userOptional = userRepository.findByEmail(registerDTO.getEmail());
        if (userOptional.isPresent()) {
            throw new DataIntegrityViolationException("Invalid data: register");
        }

        Users user = registerDTOMapper.apply(registerDTO);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        String token = jwtService.generate(user.getId(), 24);

        String subject = "Activation de votre compte Legofruit";
        String activationLink = "http://localhost:5173/account/activation/" + token;
        String body = String.format("Bonjour %s, <br/> Veillez cliquer sur ce lien pour activer votre compte: %s. A bientot !",
                user.getFirstname(), activationLink);


        mailService.send(user.getEmail(), subject, body);
    }

    public String signin(AuthenticationDTO authenticationDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationDTO.getEmail(), authenticationDTO.getPassword())
        );

        return jwtService.generate(authenticationDTO.getEmail(), 24);
    }

    public UserDTO getUserProfile(String userId) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return userDTOMapper.apply(user);
    }
}
