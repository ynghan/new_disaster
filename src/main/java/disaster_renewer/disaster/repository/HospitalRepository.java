package disaster_renewer.disaster.repository;

import disaster_renewer.disaster.domain.publicSafety.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {

}
