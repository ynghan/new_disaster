package disaster_renewer.disaster.domain.publicSafety;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
public class SafeCenter119 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "safe_center_id")
    private Long id;

    private String jurisdiction; //시도본부
    private String safeCenterName; //119안전센터명
    private String number; //전화번호
    private String address; //주소
}
