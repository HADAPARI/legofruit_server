package mg.legofruit.server.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import mg.legofruit.server.dto.AuthenticationDTO;
import mg.legofruit.server.dto.RegisterDTO;
import mg.legofruit.server.dto.UserDTO;
import mg.legofruit.server.repository.UserRepository;
import mg.legofruit.server.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void signUp(@Valid @RequestBody RegisterDTO registerDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException("Invalid data: register");
        }

        userService.signup(registerDTO);
    }

    @PostMapping("/signin")
    public void signIn(@Valid @RequestBody AuthenticationDTO authenticationDTO, BindingResult bindingResult, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException("Invalid data: login");
        }

        String token = userService.signin(authenticationDTO);

        Cookie cookie = new Cookie("at", token);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(86400); // en secondes (1 jour)
        cookie.setPath("/");

        response.addCookie(cookie);
    }

    @GetMapping("/profile/{userId}")
    public ResponseEntity<UserDTO> getUserProfile(@PathVariable String userId) {
        UserDTO userProfile = userService.getUserProfile(userId);
        return ResponseEntity.ok(userProfile);
    }
}
