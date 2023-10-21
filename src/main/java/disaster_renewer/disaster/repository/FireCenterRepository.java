package disaster_renewer.disaster.repository;

import disaster_renewer.disaster.domain.publicSafety.FireCenter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FireCenterRepository extends JpaRepository<FireCenter, Long> {
}
