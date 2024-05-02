package mg.legofruit.server.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mg.legofruit.server.entity.Users;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;

    private Users user;

    private String category;

    private String type;

    private String title;

    private Double price;

    private Integer promotion = 0;

    private Double quantity;

    private String image;

    private LocalDate harvest ;
}
