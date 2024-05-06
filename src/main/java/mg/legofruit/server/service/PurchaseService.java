package mg.legofruit.server.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mg.legofruit.server.entity.Order;
import mg.legofruit.server.entity.Product;
import mg.legofruit.server.entity.Purchase;
import mg.legofruit.server.repository.OrderRepository;
import mg.legofruit.server.repository.ProductRepository;
import mg.legofruit.server.repository.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseService {

    private PurchaseRepository purchaseRepository;
    private OrderRepository orderRepository;
    private ProductRepository productRepository;

    public Purchase makePurchase  (List<Long> idsOrders)
    {
        Purchase purchase = new Purchase();
        Double totalAmount = 0.00;
        List<Order> orders = orderRepository.findAllById(idsOrders);
        purchase.getOrders().addAll(orders);
        for (Order order : orders) {
            Product product = order.getProduct();
            product.setQuantity(product.getQuantity()-order.getQuantity());
            productRepository.save(product);
            totalAmount += product.getPrice();
        }
        purchase.setTotalAmount(totalAmount);
        return purchaseRepository.save(purchase);
    }
}
