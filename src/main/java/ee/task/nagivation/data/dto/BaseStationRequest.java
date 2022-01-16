package ee.task.nagivation.data.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class BaseStationRequest {
    @JsonProperty("base_station_id")
    UUID baseId;
    Report[] reports;

    @Data
    public static class Report {
        @JsonProperty("mobile_station_id")
        UUID mobileId;
        float distance;
        @JsonFormat(pattern = "yyyy-MM-dd-hh:mm:ss.SSS")
        LocalDateTime timestamp;
    }
}
