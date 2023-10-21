package disaster_renewer.disaster.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class PoliceDto {

    @JsonProperty("X좌표")
    private Double xCoordinate;

    @JsonProperty("Y좌표")
    private Double yCoordinate;
//
//    @JsonProperty("주소")
//    private String address;

}
