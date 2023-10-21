package disaster_renewer.disaster.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {

    private String zipcode;
    private String roadName;
    private String detailAddress;

    protected Address() {
    }

    public Address(String zipcode, String roadName, String detailAddress) {
        this.zipcode = zipcode;
        this.roadName = roadName;
        this.detailAddress = detailAddress;
    }
}
