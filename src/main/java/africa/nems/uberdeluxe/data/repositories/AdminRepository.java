package africa.nems.uberdeluxe.data.repositories;

import africa.nems.uberdeluxe.data.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
