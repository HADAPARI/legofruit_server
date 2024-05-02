package mg.legofruit.server.service;

import lombok.AllArgsConstructor;
import mg.legofruit.server.dto.ProfileReviewDTO;
import mg.legofruit.server.entity.ProfileReview;
import mg.legofruit.server.entity.Users;
import mg.legofruit.server.repository.ProfileReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProfileService {
    @Autowired
    private ProfileReviewRepository profileReviewRepository;

    public void addProfileReview(Users reviewer, Users reviewed, int rating, String comment) {
        ProfileReview review = new ProfileReview();
        review.setReviewer(reviewer);
        review.setReviewed(reviewed);
        review.setRating(rating);
        review.setComment(comment);
        profileReviewRepository.save(review);
    }

    public double calculateAverageRating(String userId) {
        List<ProfileReview> profileReviews = profileReviewRepository.findByReviewedId(userId);

        OptionalDouble averageRating = profileReviews.stream()
                .mapToInt(ProfileReview::getRating)
                .average();

        return averageRating.orElse(0.0);
    }

    public List<String> getAllProfileComments(String userId) {
        List<ProfileReview> profileReviews = profileReviewRepository.findByReviewedId(userId);

        return profileReviews.stream()
                .map(ProfileReview::getComment)
                .collect(Collectors.toList());
    }
}
