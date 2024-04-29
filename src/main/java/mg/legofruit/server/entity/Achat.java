package mg.legofruit.server.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Achat {

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
    private String image;

}
