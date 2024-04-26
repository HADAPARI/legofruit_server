package mg.legofruit.server.repository;

import mg.legofruit.server.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EditeUserRepository extends JpaRepository<Users, String> {

}
