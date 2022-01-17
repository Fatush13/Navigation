package ee.task.nagivation.util;


import ee.task.nagivation.data.MobileStation;
import ee.task.nagivation.data.dto.MobileStationResponse;


public class ConvertUtil {

   public static MobileStationResponse convertToResponse(MobileStation mobileStation) {
      return MobileStationResponse.builder()
           .mobileId(mobileStation.getId())
           .x(mobileStation.getLastKnownX())
           .y(mobileStation.getLastKnownY())
           .errorRadius(mobileStation.getErrorRadius())
           .build();
   }
}
