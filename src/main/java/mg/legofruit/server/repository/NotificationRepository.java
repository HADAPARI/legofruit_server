package mg.legofruit.server.repository;

import mg.legofruit.server.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByRecipientEmail(String email);

    List<Notification> findByRecipientId(String userId);
}

