package mg.legofruit.server.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Users userid;

    @Column(nullable = false)
    private Integer category;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double prix;

    @Column(nullable = false)
    private Double quantity;

    @Column(nullable = false)
    private LocalDate datepublication;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private LocalDate daterecolte = LocalDate.now();
}
