package mg.legofruit.server.mapper;

import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import mg.legofruit.server.dto.OrderDTO;
import mg.legofruit.server.entity.Order;
import mg.legofruit.server.entity.Product;
import mg.legofruit.server.entity.Users;
import mg.legofruit.server.repository.ProductRepository;
import mg.legofruit.server.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@AllArgsConstructor
public class OrderDTOMapper implements Function<OrderDTO, Order> {
    private ProductRepository productRepository;
    private UserRepository userRepository;
    @Override
    public Order apply(OrderDTO orderDTO) {
        Order order = new Order();
        Product product = productRepository.findById(orderDTO.getProduct()).orElseThrow(()-> new ValidationException("product not found"));
        Double unitPrice = product.getPrice();
        order.setProduct(product);
        order.setQuantity(orderDTO.getQuantity());
        order.setPrice(unitPrice * orderDTO.getQuantity());

        return order;
    }
}
