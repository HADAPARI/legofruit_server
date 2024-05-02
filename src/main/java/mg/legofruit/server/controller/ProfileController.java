package mg.legofruit.server.controller;

import lombok.AllArgsConstructor;
import mg.legofruit.server.dto.ProfileReviewDTO;
import mg.legofruit.server.dto.UserDTO;
import mg.legofruit.server.entity.ProfileReview;
import mg.legofruit.server.entity.Users;
import mg.legofruit.server.repository.ProfileReviewRepository;
import mg.legofruit.server.service.ProfileService;
import mg.legofruit.server.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/review")
@AllArgsConstructor
public class ProfileController {

    private final UserService userService;
    private final ProfileService profileService;
    private final ProfileReviewRepository profileReviewRepository;

    @PostMapping("/{userId}/reviews")
    public ResponseEntity<String> addProfileReview(
            @PathVariable("userId") String userId,
            @RequestBody ProfileReviewDTO profileReviewDTO) {

        String reviewedUserId = profileReviewDTO.getReviewedUserId();
        int rating = profileReviewDTO.getRating();
        String comment = profileReviewDTO.getComment();

        UserDTO reviewerDTO = userService.getUserProfile(userId);
        UserDTO reviewedDTO = userService.getUserProfile(reviewedUserId);

        if (reviewerDTO == null || reviewedDTO == null) {
            return ResponseEntity.badRequest().body("Invalid user(s).");
        }

        Users reviewer = convertUserDTOToUsers(reviewerDTO);
        Users reviewed = convertUserDTOToUsers(reviewedDTO);

        profileService.addProfileReview(reviewer, reviewed, rating, comment);

        return ResponseEntity.ok("Review added successfully.");
    }

    private Users convertUserDTOToUsers(UserDTO userDTO) {
        Users user = userService.getUserById(userDTO.getId());
        if (user == null) {
            throw new RuntimeException("User not found for id: " + userDTO.getId());
        }

        user.setFirstname(userDTO.getFirstname());
        user.setLastname(userDTO.getLastname());
        user.setEmail(userDTO.getEmail());
        user.setAvatar(userDTO.getAvatar());
        user.setPhone(userDTO.getPhone());
        user.setAddress(userDTO.getAddress());

        return user;
    }

    @GetMapping("/average-ratings/{userId}")
    public ResponseEntity<Double> getAverageRating(@PathVariable String userId) {
        double averageRating = profileService.calculateAverageRating(userId);

        if (averageRating == 0.0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(averageRating);
    }


    @GetMapping("/comment/{userId}")
    public ResponseEntity<List<String>> getAllProfileComments(@PathVariable("userId") String userId) {
        List<String> profileComments = profileService.getAllProfileComments(userId);
        return ResponseEntity.ok(profileComments);
    }
}
