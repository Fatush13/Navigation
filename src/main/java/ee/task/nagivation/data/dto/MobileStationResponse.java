package ee.task.nagivation.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.UUID;

@Builder
public class MobileStationResponse {
    UUID mobileId;
    float x;
    float y;
    @JsonProperty("error_radius")
    float errorRadius;
    @JsonProperty("error_code")
    int errorCode;
    @JsonProperty("error_description")
    String errorDescription;

}
