package ee.task.nagivation.data.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;


@Data
@NoArgsConstructor
public class BaseStationRequest {
   @NonNull
   @JsonProperty ("base_station_id")
   UUID baseId;
   Report[] reports;

   @Data
   @NoArgsConstructor
   public static class Report {
      @NonNull
      @JsonProperty ("mobile_station_id")
      UUID mobileId;
      @NonNull
      float distance;
      @NonNull
      @DateTimeFormat (pattern = "yyyy-MM-dd hh:mm:ss.SSS")
      LocalDateTime timestamp;
   }

}
