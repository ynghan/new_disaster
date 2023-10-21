package disaster_renewer.disaster.domain.publicSafety;

import disaster_renewer.disaster.domain.Location;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Police {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "police_id")
    private Long id;

    //경찰청
    private String policeAgency;
    //경찰서
    private String policeStation;
    //파출소
    private String policeBox;

    @Embedded
    private Location location;

    private String address;
}
