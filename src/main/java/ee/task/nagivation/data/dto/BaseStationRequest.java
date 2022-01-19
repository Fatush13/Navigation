package ee.task.nagivation.data.dto;


import java.time.Instant;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonProperty;


@Data
public class BaseStationRequest {
   @NotNull (message = "No base station id is presented")
   @JsonProperty ("base_station_id")
   UUID baseId;
   @Valid @NotEmpty (message = "Request must contain at least 1 report")
   Report[] reports;

   @Data
   public static class Report {
      @NotNull (message = "No mobile station id is presented")
      @JsonProperty ("mobile_station_id")
      UUID mobileId;
      @NotNull (message = "No distance value is presented")
      Float distance;
      @NotNull (message = "No timestamp value is presented")
      Instant timestamp;
   }

}
