package mg.legofruit.server.repository;

import mg.legofruit.server.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region,Integer> {
}
