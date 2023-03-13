package africa.nems.uberdeluxe.data.repositories;

import africa.nems.uberdeluxe.data.models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, Long> {
}
