package mg.legofruit.server.service;

import lombok.AllArgsConstructor;
import mg.legofruit.server.ProduitSpecification.ProduitSpecification;
import mg.legofruit.server.dto.ProductDTO;
import mg.legofruit.server.entity.Product;
import mg.legofruit.server.entity.Users;
import mg.legofruit.server.mapper.ProductDTOMapper;
import mg.legofruit.server.repository.ProductRepository;
import mg.legofruit.server.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductDTOMapper productDTOMapper;
    private final UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    public ProductDTO addNewProduct(ProductDTO productDTO, String userId) {
        logger.info("Adding new product with userID: {}", userId);
        Optional<Users> userOptional = userRepository.findById(userId);

        if (userOptional.isEmpty()) {
            logger.error("Utilisateur introuvable pour l'ID: {}", userId);
            throw new RuntimeException("Utilisateur introuvable");
        }

        Users user = userOptional.get();

        Product product = new Product();
        product.setUser(user);
        product.setCategory(productDTO.getCategory());
        product.setTitle(productDTO.getTitle());
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());
        product.setHarvest(productDTO.getHarvest());
        product.setImage(productDTO.getImage());

        Product savedProduct = productRepository.save(product);

        return productDTOMapper.apply(savedProduct);
    }
    public List<Product> findAllProduct(String name, String category) {
        final Specification<Product> specification =
                ProduitSpecification.filterProduct(category, name);
        final List<Product> product = productRepository.findAll(specification);
        return product;
    }

    public List<Product> getAllProduit(){
        return productRepository.findAll();
    }

 }
