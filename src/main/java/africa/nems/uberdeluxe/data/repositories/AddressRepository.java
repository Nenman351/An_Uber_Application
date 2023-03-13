package africa.nems.uberdeluxe.data.repositories;

import africa.nems.uberdeluxe.data.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
