package mg.legofruit.server.repository;

import mg.legofruit.server.dto.UserDTO;
import mg.legofruit.server.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,String> {
    Optional<Users> findByEmail(String email);
    Users findByLastname(String username);
}
