package mg.legofruit.server.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import mg.legofruit.server.dto.OrderDTO;
import mg.legofruit.server.entity.Order;
import mg.legofruit.server.entity.Purchase;
import mg.legofruit.server.service.PurchaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/purchase")
@AllArgsConstructor
public class PurchaseController {
    private PurchaseService purchaseService;

    @PostMapping("/buy")
    public ResponseEntity buyProducts (@RequestBody List<Long> idsOrder) {
        Purchase buy = purchaseService.makePurchase(idsOrder);
        return ResponseEntity.ok(buy);
    }
}
