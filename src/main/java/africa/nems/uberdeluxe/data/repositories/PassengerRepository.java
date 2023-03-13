package africa.nems.uberdeluxe.data.repositories;

import africa.nems.uberdeluxe.data.models.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
}
