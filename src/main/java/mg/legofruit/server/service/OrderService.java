package mg.legofruit.server.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import mg.legofruit.server.dto.CategoryDTO;
import mg.legofruit.server.dto.OrderDTO;
import mg.legofruit.server.entity.Order;
import mg.legofruit.server.entity.Subscription;
import mg.legofruit.server.entity.Users;
import mg.legofruit.server.mapper.OrderDTOMapper;
import mg.legofruit.server.repository.OrderRepository;
import mg.legofruit.server.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;
    private OrderDTOMapper orderDTOMapper;
    private UserRepository userRepository;
    private JWTService jwtService;

    public Order addOrder (String token, OrderDTO orderDTO) {
        String userEmail = jwtService.decode(token);
        Users user = userRepository.findByEmail(userEmail).orElseThrow(() -> new RuntimeException("User not found"));
        Order order = orderDTOMapper.apply(orderDTO);
       // order.setUsers(user);

        return orderRepository.save(order);
    }
}
