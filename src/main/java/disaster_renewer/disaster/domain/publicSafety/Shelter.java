package disaster_renewer.disaster.domain.publicSafety;


import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
public class Shelter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String areaCode;
    private String address;
    private Double latitude;
    private Double longitude;
    private Double capacity;
}
