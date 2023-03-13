package africa.nems.uberdeluxe.data.repositories;

import africa.nems.uberdeluxe.data.models.BankInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankInfoRepository extends JpaRepository<BankInfo, Long> {
}
