package mg.legofruit.server.controller;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import mg.legofruit.server.dto.RegisterDTO;
import mg.legofruit.server.service.UserService;
import org.springframework.http.HttpStatus;
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

    @GetMapping("activate/{token}")
    public void activate(@PathVariable String token) {
        userService.activate(token);
    }
}
