package disaster_renewer.disaster.domain;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true)
    private String identification;

    private String password;

    @NotNull
    private String username;

    @NotNull
    private String personalId;

    @NotNull
    private int age;

    @NotNull
    private String sex;

    @NotNull
    private String phoneNum;

    @NotNull
    @Embedded
    private Address address;

    private Location location;

    @OneToMany(mappedBy = "owner")
    private List<Friendship> friendships = new ArrayList<>();


    public void addFriend(Member friend) {
        Friendship friendship = new Friendship(this, friend);
        friendships.add(friendship);
    }

    public void removeFriend(Member friend) {
        friendships.removeIf(friendship -> friendship.getFriend().equals(friend));
    }

}
