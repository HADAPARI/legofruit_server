package mg.legofruit.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProfileReviewDTO {
    private String reviewedUserId;
    private int rating;
    private String comment;
}
