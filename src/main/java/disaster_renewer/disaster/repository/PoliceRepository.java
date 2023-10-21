package disaster_renewer.disaster.repository;

import disaster_renewer.disaster.domain.publicSafety.Police;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PoliceRepository extends JpaRepository<Police, Long> {
}
