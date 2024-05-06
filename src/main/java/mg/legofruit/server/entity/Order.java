package mg.legofruit.server.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Users users;

    @Column (nullable = false)
    private Integer quantity;

    @Column (nullable = false)
    private Double price;

    @JoinColumn (nullable = false)
    @ManyToOne
    private  Product product;

    @ManyToMany(mappedBy = "orders")
    private Set<Purchase> purchase = new HashSet<>();

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

}
