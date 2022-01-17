package ee.task.nagivation;


import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.extern.slf4j.Slf4j;


import ee.task.nagivation.data.MobileStation;
import ee.task.nagivation.data.ReportedPosition;
import ee.task.nagivation.service.CalculationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


@Slf4j
public class TestCalculationService extends Assertions {

   CalculationService service = new CalculationService();

//   ReportedPosition firstReport = ReportedPosition.builder()
//        .x(1.44f)
//        .y(1.46f)
//        .errorRadius(1.22f)
//        .build();
//
//   ReportedPosition secondReport = ReportedPosition.builder()
//        .x(3.92f)
//        .y(3.46f)
//        .errorRadius(2.31f)
//        .build();
//
//   ReportedPosition thirdPosition = ReportedPosition.builder()
//        .x(3.82f)
//        .y(-0.44f)
//        .errorRadius(3.11f)
//        .build();


   ReportedPosition firstReport = ReportedPosition.builder()
        .x(2.29f)
        .y(2.21f)
        .errorRadius(1.72f)
        .build();

   ReportedPosition secondReport = ReportedPosition.builder()
        .x(1.73f)
        .y(2.74f)
        .errorRadius(1.38f)
        .build();

   ReportedPosition thirdPosition = ReportedPosition.builder()
        .x(3.27f)
        .y(1.78f)
        .errorRadius(1.59f)
        .build();

   @Test
   void testByOneStation() {
      MobileStation mobileStation = service.calculatePosition(
           Stream.of(firstReport).collect(Collectors.toList()));

      assertEquals(5.0f, mobileStation.getLastKnownX());
      assertEquals(6.0f, mobileStation.getLastKnownY());
      assertEquals(2.0f, mobileStation.getErrorRadius());
   }

   @Test
   void testByTwoStation() {

   }

   @Test
   void testByThreeStation() {
      MobileStation mobileStation = service.calculatePosition(
           Stream.of(firstReport, secondReport, thirdPosition).collect(Collectors.toList()));

      log.error("MobileStation: {}", mobileStation);
   }

}
