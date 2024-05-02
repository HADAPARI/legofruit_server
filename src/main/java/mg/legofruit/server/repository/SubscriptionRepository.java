package mg.legofruit.server.repository;
import mg.legofruit.server.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
public interface SubscriptionRepository extends JpaRepository<Subscription,Long> {
}
