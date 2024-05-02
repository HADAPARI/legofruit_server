package mg.legofruit.server.mapper;

import mg.legofruit.server.dto.ProductDTO;
import mg.legofruit.server.entity.Product;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ProductDTOMapper implements Function<Product, ProductDTO> {

    @Override
    public ProductDTO apply(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getUser(),
                product.getCategory(),
                product.getType(),
                product.getTitle(),
                product.getPrice(),
                product.getPromotion(),
                product.getQuantity(),
                product.getImage(),
                product.getHarvest()
        );
    }
}