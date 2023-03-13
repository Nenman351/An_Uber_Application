package africa.nems.uberdeluxe.data.repositories;

import africa.nems.uberdeluxe.data.models.Detail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<Detail, Long> {
}
