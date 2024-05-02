package mg.legofruit.server.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    @JoinColumn(nullable = false)
    @ManyToOne
    private Category category;

    //utile pour l'admin, à cogiter plus tard
    /*@OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users users;*/
}
