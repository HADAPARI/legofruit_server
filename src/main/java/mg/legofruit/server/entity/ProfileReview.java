package mg.legofruit.server.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reviewer_id", nullable = false)
    private Users reviewer;

    @ManyToOne
    @JoinColumn(name = "reviewed_id", nullable = false)
    private Users reviewed;

    @Column(nullable = false)
    private int rating;

    @Column(nullable = false, length = 1000000)
    private String comment;

    @Column(nullable = false)
    private LocalDate createdAt = LocalDate.now();
}
