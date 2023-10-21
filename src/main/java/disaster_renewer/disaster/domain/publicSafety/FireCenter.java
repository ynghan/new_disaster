package disaster_renewer.disaster.domain.publicSafety;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
public class FireCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fire_center_id")
    private Long id;

    private String jurisdiction;
    private String fireCenterName;
    private String number;
    private String address;

}
