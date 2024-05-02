package mg.legofruit.server.repository;

import mg.legofruit.server.entity.ProfileReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

public interface ProfileReviewRepository extends JpaRepository<ProfileReview, String> {

    Collection<Object> findAllByReviewedId(String userId);

    List<ProfileReview> findByReviewedId(String userId);
}

