package ee.task.nagivation.util;


import ee.task.nagivation.data.ReportedPosition;


public class IntersectionUtil {

   public static boolean isOverLapping(ReportedPosition firstCircle, ReportedPosition secondCircle) {
      float x1 = firstCircle.getX();
      float y1 = firstCircle.getY();
      float r1 = firstCircle.getErrorRadius();
      float x2 = secondCircle.getX();
      float y2 = secondCircle.getY();
      float r2 = secondCircle.getErrorRadius();

      float distSq = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
      float radSumSq = (r1 + r2) * (r1 + r2);

      return !(distSq > radSumSq);
   }
}
