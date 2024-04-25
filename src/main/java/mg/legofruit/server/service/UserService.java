package mg.legofruit.server.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mg.legofruit.server.dto.RegisterDTO;
import mg.legofruit.server.entity.Users;
import mg.legofruit.server.mapper.RegisterDTOMapper;
import mg.legofruit.server.repository.UserRepository;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
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
    private ResourceLoader resourceLoader;


    public void signup(RegisterDTO registerDTO) {
        Optional<Users> userOptional = userRepository.findByEmail(registerDTO.getEmail());
        if (userOptional.isPresent()) {
            throw new DataIntegrityViolationException("Invalid data: register");
        }

        Users user = registerDTOMapper.apply(registerDTO);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        String token = jwtService.generate(user.getId(), 24);

        String subject = "Activation compte Legofruit";

        Resource resource = resourceLoader.getResource("classpath:templates/activation.html");
        String htmlTemplate = "";
        try (InputStreamReader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)) {
            htmlTemplate = FileCopyUtils.copyToString(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        htmlTemplate = htmlTemplate.replace("{{firstname}}", StringUtils.hasText(user.getFirstname()) ? user.getFirstname() : "");
        htmlTemplate = htmlTemplate.replace("{{token}}", StringUtils.hasText(token) ? token : "");

        mailService.send(user.getEmail(), htmlTemplate , subject);
    }

    public void activate(String token) {
        boolean isValidToken = jwtService.verify(token);

        if (!isValidToken) {
            throw new DataIntegrityViolationException("Invalid Link");
        }

        String userId = jwtService.decode(token);

        Optional<Users> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new DataIntegrityViolationException("Invalid link");
        }

        Users user = userOptional.get();
        user.setActive(true);
        userRepository.save(user);
    }
}
