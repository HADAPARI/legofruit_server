package mg.legofruit.server.service;

import lombok.AllArgsConstructor;
import mg.legofruit.server.dto.NotificationDTO;
import mg.legofruit.server.dto.UserDTO;
import mg.legofruit.server.entity.Notification;
import mg.legofruit.server.entity.Users;
import mg.legofruit.server.repository.NotificationRepository;
import mg.legofruit.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    private final JWTService jwtService;

    public void sendNotification(String token, NotificationDTO notificationDTO) {
        String senderEmail = jwtService.decode(token);

        Users sender = userRepository.findByEmail(senderEmail)
                .orElseThrow(() -> new RuntimeException("Utilisateur expéditeur introuvable"));

        String recipientId =  notificationDTO.getRecipientId();

        Users recipient = userRepository.findById(recipientId)
                .orElseThrow(() -> new RuntimeException("Utilisateur destinataire introuvable"));

        String message = notificationDTO.getMessage();
        Notification notification = new Notification();
        notification.setSender(sender);
        notification.setRecipient(recipient);
        notification.setMessage(message);
        notification.setCreatedAt(LocalDateTime.now());
        notificationRepository.save(notification);
    }

    public List<Notification> getNotificationsForUser(String token) {
        String senderEmail = jwtService.decode(token);

        Users sender = userRepository.findByEmail(senderEmail)
                .orElseThrow(() -> new RuntimeException("Utilisateur expéditeur introuvable"));

        List<Notification> userNotifications = notificationRepository.findByRecipientId(sender.getId());
        return userNotifications;
    }
}

