package africa.nems.uberdeluxe.data.repositories;

import africa.nems.uberdeluxe.data.models.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
}
