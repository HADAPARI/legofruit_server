package mg.legofruit.server.repository;

import mg.legofruit.server.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country,Integer> {
    Country findByName(String name);
}
