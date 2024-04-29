package mg.legofruit.server.repository;


import mg.legofruit.server.entity.Achat;
import mg.legofruit.server.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AchatRepository extends JpaRepository<Achat, Long> {
    List<Achat> findByUserid(Users userid);
}