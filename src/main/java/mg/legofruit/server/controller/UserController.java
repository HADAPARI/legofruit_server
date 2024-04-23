package mg.legofruit.server.controller;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import mg.legofruit.server.dto.UserSignUpDTO;
import mg.legofruit.server.dto.UserSingInDTO;
import mg.legofruit.server.service.AppUserDetailsService;
import mg.legofruit.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/signup")
    public void signUp() {
        log.info("hereeeeeeeeee");
    }

    @PostMapping("/signin")
    public ResponseEntity<UserDetails> signin(@Valid @RequestBody UserSingInDTO userSingInDTO, BindingResult result) {
        if (result.hasErrors()) {
            log.info("Invalid data signin");
            throw new ValidationException("Invalid data signin");
        }
        UserDetails userDetails = userService.signin(userSingInDTO);
        return ResponseEntity.ok(userDetails);
    }

}



