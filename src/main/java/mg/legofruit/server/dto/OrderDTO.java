package mg.legofruit.server.dto;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mg.legofruit.server.entity.Product;
import mg.legofruit.server.entity.Users;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private Integer quantity;

    private Double price;

    private Long product;
}
