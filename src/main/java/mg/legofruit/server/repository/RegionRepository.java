package mg.legofruit.server.repository;

import mg.legofruit.server.entity.Country;
import mg.legofruit.server.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface RegionRepository extends JpaRepository<Region,Integer> {
    List<Region> findAllByCountry(Country country);
}
