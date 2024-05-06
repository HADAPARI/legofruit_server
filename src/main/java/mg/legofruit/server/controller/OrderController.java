package mg.legofruit.server.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import mg.legofruit.server.dto.CategoryDTO;
import mg.legofruit.server.dto.OrderDTO;
import mg.legofruit.server.entity.Order;
import mg.legofruit.server.entity.Users;
import mg.legofruit.server.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {
    private OrderService orderService;

    @PostMapping("/makeorder")
    public ResponseEntity subscribeUser (@RequestBody OrderDTO orderDTO, HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        String token = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("at".equals(cookie.getName())) {
                    token = cookie.getValue();
                }
            }
        }
        Order makeOrder = orderService.addOrder(token, orderDTO);
        return ResponseEntity.ok(makeOrder);
    }
}
