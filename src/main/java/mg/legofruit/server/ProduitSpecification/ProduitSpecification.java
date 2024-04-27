package mg.legofruit.server.ProduitSpecification;

import mg.legofruit.server.entity.Produit;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;

//import java.util.function.Predicate;

public class ProduitSpecification {

    public static Specification<Produit> filterProduct(String name, String category) {
        return (root, query, criteriaBuilder) -> {
            Predicate namePredicate =
                    criteriaBuilder.like(root.get("name"), StringUtils.isBlank(name)
                            ? likePattern("") : name);
            Predicate categoryPredicate =
                    criteriaBuilder.like(root.get("category"), StringUtils.isBlank(category)
                            ? likePattern("") : category);
            return criteriaBuilder.and(namePredicate, categoryPredicate);
        };
    }

    private static String likePattern(String value) {
        return "%" + value + "%";
    }
}
