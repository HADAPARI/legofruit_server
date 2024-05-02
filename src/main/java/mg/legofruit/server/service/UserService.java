package mg.legofruit.server.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mg.legofruit.server.dto.*;
import mg.legofruit.server.entity.Category;
import mg.legofruit.server.entity.Subscription;
import mg.legofruit.server.entity.Users;
import mg.legofruit.server.mapper.RegisterDTOMapper;
import mg.legofruit.server.mapper.SubscriptionDTOMapper;
import mg.legofruit.server.mapper.UserDTOMapper;
import mg.legofruit.server.repository.*;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    private AuthenticationManager authenticationManager;
    private ResourceLoader resourceLoader;
    private UserDTOMapper userDTOMapper;
    private EditeUserRepository editeUserRepository;
    private RegionRepository regionRepository;
    private CountryRepository countryRepository;
    private CategoryRepository categoryRepository;
    private SubscriptionDTOMapper subscriptionDTOMapper;
    private SubscriptionRepository subscriptionRepository;

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

    public Object[] signin(AuthenticationDTO authenticationDTO) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationDTO.getEmail(), authenticationDTO.getPassword())
        );

        Optional<Users> users = userRepository.findByEmail(authenticationDTO.getEmail());

        if (users.isEmpty()) {
            throw new IllegalStateException("Authent error");
        }

        UserDTO userDTO = userDTOMapper.apply(users.get());

        String token = jwtService.generate(authenticationDTO.getEmail(), 24);
        return new Object[]{token, userDTO};
    }
    public ResponseEntity<UserDTO> isConnected(String token) {
        if (token == null){
            return null;
        }else {
            String userId = jwtService.decode(token);
            Optional<Users> userOptional = userRepository.findByEmail(userId);
            if (userOptional.isEmpty()) {
                return null;
            }
            UserDTO userDTO = userDTOMapper.apply(userOptional.get());

            return ResponseEntity.ok(userDTO);
        }
    }
    public Users updateUser(String id, EditeUserDTO editeUserDTO) {
        System.out.println("Updating user with ID: " + id);


        Users userEdite = editeUserRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur introuvable avec l’identifiant: " + id));

        System.out.println("Found user: " + userEdite);


        System.out.println("New user data: " + editeUserDTO);

        userEdite.setFirstname(editeUserDTO.getFirstname());
        userEdite.setLastname(editeUserDTO.getLastname());
        userEdite.setEmail(editeUserDTO.getEmail());
        userEdite.setPhone(editeUserDTO.getPhone());
        userEdite.setAddress(editeUserDTO.getAddress());
        userEdite.setRegion(regionRepository.findById(editeUserDTO.getRegion()).orElseThrow(() -> new RuntimeException("Invalid region")));
        userEdite.setCountry(countryRepository.findById(editeUserDTO.getCountry()).orElseThrow(() -> new RuntimeException("Invalid country")));

        Users updatedUser = editeUserRepository.save(userEdite);
        System.out.println("Updated user: " + updatedUser);

        return updatedUser;
    }
    public UserDTO getUserProfile(String token) {
        if (token == null){
            return null;
        }else {
            String userId = jwtService.decode(token);
            Optional<Users> userOptional = userRepository.findByEmail(userId);
            return userOptional.map(users -> userDTOMapper.apply(users)).orElse(null);
        }
    }

    public Users subscribeToCategory (String token, CategoryDTO categoryDTO) {
        String userEmail = jwtService.decode(token);
        Users user = userRepository.findByEmail(userEmail).orElseThrow(() -> new RuntimeException("User not found"));
        Subscription subscription = subscriptionDTOMapper.apply(categoryDTO);
        subscriptionRepository.save(subscription);
        user.setSubscription(subscription);
        return userRepository.save(user);
    }


}
