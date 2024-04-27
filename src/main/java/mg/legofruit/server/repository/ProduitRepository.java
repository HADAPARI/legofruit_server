package mg.legofruit.server.repository;

import mg.legofruit.server.entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProduitRepository extends JpaRepository<Produit, Long> {
}
