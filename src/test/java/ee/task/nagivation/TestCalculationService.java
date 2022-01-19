package ee.task.nagivation;


import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import ee.task.nagivation.data.MobileStation;
import ee.task.nagivation.data.ReportedPosition;
import ee.task.nagivation.service.CalculationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static java.time.temporal.ChronoUnit.SECONDS;


public class TestCalculationService extends Assertions {

   CalculationService service = new CalculationService();

   List<ReportedPosition> reports = new ArrayList<>();

   @Test
   void testByOneStation() {
      addReport(2.29f, 2.21f, 1.72f, Instant.now().minus(Duration.of(1, SECONDS)));

      MobileStation mobileStation = service.calculatePosition(reports);

      assertEquals(2.29f, mobileStation.getLastKnownX());
      assertEquals(2.21f, mobileStation.getLastKnownY());
      assertEquals(1.72f, mobileStation.getErrorRadius());
   }

   @Test
   void testByThreeStation() {
      addReport(3.27f, 1.78f, 1.59f, Instant.now().minus(Duration.of(3, SECONDS)));
      addReport(1.73f, 2.74f, 1.38f, Instant.now().minus(Duration.of(2, SECONDS)));
      addReport(2.29f, 2.21f, 1.72f, Instant.now().minus(Duration.of(1, SECONDS)));

      MobileStation mobileStation = service.calculatePosition(reports);

      assertTrue((2.6f - 1.1f <= mobileStation.getLastKnownX() && mobileStation.getLastKnownX() <= 2.5f + 1.1f));
      assertTrue((2.4f - 1.5f <= mobileStation.getLastKnownY() && mobileStation.getLastKnownY() <= 2.4f + 1.5f));
      assertTrue(mobileStation.getErrorRadius() <= 1.2f);
   }

   private void addReport(float x, float y, float radius, Instant timestamp) {
      reports.add(ReportedPosition.builder()
           .x(x)
           .y(y)
           .errorRadius(radius)
           .timestamp(timestamp)
           .build());
   }

}
