package ee.task.nagivation;


import ee.task.nagivation.data.ReportedPosition;
import ee.task.nagivation.util.IntersectionUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class TestIntersectionUtil extends Assertions {

   ReportedPosition firstCircle = ReportedPosition.builder()
        .x(2.02f)
        .y(2.34f)
        .errorRadius(2.05f)
        .build();

   @Test
   void isInsideTest() {
      ReportedPosition secondCircle = ReportedPosition.builder()
           .x(3.03f)
           .y(2.17f)
           .errorRadius(0.51f)
           .build();

      assertTrue(IntersectionUtil.isOverLapping(firstCircle, secondCircle));
   }

   @Test
   void isOverlapping() {
      ReportedPosition secondCircle = ReportedPosition.builder()
           .x(5.23f)
           .y(4.17f)
           .errorRadius(1.98f)
           .build();

      assertTrue(IntersectionUtil.isOverLapping(firstCircle, secondCircle));
   }

   @Test
   void isNotOverlapping() {
      ReportedPosition secondCircle = ReportedPosition.builder()
           .x(5.92f)
           .y(6.02f)
           .errorRadius(1.76f)
           .build();

      assertFalse(IntersectionUtil.isOverLapping(firstCircle, secondCircle));
   }

}
