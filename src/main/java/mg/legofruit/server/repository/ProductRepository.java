package mg.legofruit.server.repository;

import mg.legofruit.server.entity.Product;
import mg.legofruit.server.entity.Users;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    public List<Product> findAll(Specification<Product> specification);
    Optional<Product> findByIdAndUser(Long id, Users user);
}
