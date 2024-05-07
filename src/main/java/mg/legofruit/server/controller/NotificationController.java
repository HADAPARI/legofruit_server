package mg.legofruit.server.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mg.legofruit.server.dto.NotificationDTO;
import mg.legofruit.server.entity.Notification;
import mg.legofruit.server.mapper.NotificationDTOMapper;
import mg.legofruit.server.service.JWTService;
import mg.legofruit.server.service.NotificationService;
import mg.legofruit.server.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/notifications")
@AllArgsConstructor
@Slf4j
public class NotificationController {

    private final NotificationService notificationService;
    private final UserService userService;
    private final NotificationDTOMapper notificationDTOMapper;
    private final JWTService jwtService;

    @PostMapping("/send")
    public ResponseEntity<?> sendNotification(HttpServletRequest request, @RequestBody NotificationDTO notificationDTO) {
        Cookie[] cookies = request.getCookies();
        String token = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("at".equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
            }
        }
        if (token == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        notificationService.sendNotification(token, notificationDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/user")
    public List<Notification> getNotificationsForUser(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String token = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("at".equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
            }
        }

        if (token == null) {
            System.out.println("Token not found in cookies.");
            return (List<Notification>) ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        List<Notification> notifications = notificationService.getNotificationsForUser(token);
        return notifications;
    }

}

