package disaster_renewer.disaster.repository;

import disaster_renewer.disaster.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByIdentification(String identification);


}
