package mg.legofruit.server.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mg.legofruit.server.dto.AuthenticationDTO;
import mg.legofruit.server.dto.RegisterDTO;
import mg.legofruit.server.dto.UserDTO;
import mg.legofruit.server.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@Slf4j
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
    public ResponseEntity<UserDTO> signIn(@Valid @RequestBody AuthenticationDTO authenticationDTO, BindingResult bindingResult, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException("Invalid data: login");
        }

        Object[] signinResponse = userService.signin(authenticationDTO);

        String token = (String) signinResponse[0];
        UserDTO userDTO = (UserDTO) signinResponse[1];

        Cookie cookie = new Cookie("at", token);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(86400); // en secondes (1 jour)
        cookie.setPath("/");

        response.addCookie(cookie);
        return ResponseEntity.ok().body(userDTO);
    }

    @GetMapping("activate/{token}")
    public void activate(@PathVariable String token) {
        userService.activate(token);
    }

    @GetMapping("isconnected")
    public ResponseEntity<UserDTO> isConnected(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String token = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("at".equals(cookie.getName())) {
                    token = cookie.getValue();
                }
            }
        }

        return userService.isConnected(token);
    }

    @GetMapping("/signout")
    public void signOut(HttpServletResponse response) {
        Cookie cookie = new Cookie("at", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");

        response.addCookie(cookie);
    }
}
