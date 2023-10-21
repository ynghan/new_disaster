package disaster_renewer.disaster.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Friendship {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Friendship(Member owner, Member friend) {
        this.owner = owner;
        this.friend = friend;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Member owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "friend_id")
    private Member friend;
}
