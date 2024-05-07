package mg.legofruit.server.mapper;

import mg.legofruit.server.dto.NotificationDTO;
import mg.legofruit.server.entity.Notification;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class NotificationDTOMapper implements Function<Notification, NotificationDTO> {
    @Override
    public NotificationDTO apply(Notification notification) {
        NotificationDTO dto = new NotificationDTO();
        dto.setRecipientId(notification.getRecipient().getId());
        dto.setMessage(notification.getMessage());
        return dto;
    }
}
