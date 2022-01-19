package ee.task.nagivation;


import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import ee.task.nagivation.data.ReportedPosition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static ee.task.nagivation.util.FiltrationUtil.filterReports;
import static java.time.temporal.ChronoUnit.SECONDS;


public class TestFiltrationUtil extends Assertions {

   List<ReportedPosition> reports = new ArrayList<>();

   @Test
   void TestFilter() {
      addReport(2.05f, 2.75f, 1.98f, Instant.now().minus(Duration.of(6, SECONDS)));
      addReport(4.17f, 5.63f, 2.12f, Instant.now().minus(Duration.of(4, SECONDS)));
      addReport(3.27f, 1.78f,1.59f, Instant.now().minus(Duration.of(3, SECONDS)));
      addReport(1.73f, 2.74f, 1.38f, Instant.now().minus(Duration.of(2, SECONDS)));
      addReport(2.29f, 2.21f, 1.72f, Instant.now().minus(Duration.of(1, SECONDS)));

      reports = filterReports(reports, Duration.of(5, SECONDS));

      assertEquals(3, reports.size(), "size");
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
