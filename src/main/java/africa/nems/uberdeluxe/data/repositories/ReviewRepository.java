package africa.nems.uberdeluxe.data.repositories;

import africa.nems.uberdeluxe.data.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Long> {

}
