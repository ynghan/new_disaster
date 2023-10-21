package disaster_renewer.disaster.domain.publicSafety;

import disaster_renewer.disaster.domain.Location;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Hospital {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hospital_id")
    private Long id;

    private String hospitalName;

    private String address;

    @Embedded
    private Location location;

}
