package mg.legofruit.server.controller;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import mg.legofruit.server.dto.UserSignUpDTO;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/signup")
    public void signUp() {
        log.info("hereeeeeeeeee");

    }
}
