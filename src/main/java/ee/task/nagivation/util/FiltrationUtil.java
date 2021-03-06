package ee.task.nagivation.util;


import java.time.Duration;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import ee.task.nagivation.data.ReportedPosition;


public class FiltrationUtil {

   public static List<ReportedPosition> filterReports(List<ReportedPosition> reports, Duration expirationTime)
   {
      reports.sort(Comparator.comparing(ReportedPosition::getTimestamp));

      List<ReportedPosition> filteredByTime = filterByTime(reports, expirationTime);

      return filterByPosition(filteredByTime);
   }

   private static List<ReportedPosition> filterByTime(List<ReportedPosition> reports, Duration expirationTime)
   {               // filters out reports by expiration time
      return reports.stream()
           .filter(report -> report.getTimestamp()
                .isAfter(reports.get(reports.size() - 1).getTimestamp().minus(expirationTime)))
           .collect(Collectors.toList());
   }

   private static List<ReportedPosition> filterByPosition(List<ReportedPosition> reports)
   {          //  filters out reported position that do not overlap with the latest position
      return reports.stream()
           .filter(report -> IntersectionUtil.isOverLapping(reports.get(reports.size() - 1), report))
           .collect(Collectors.toList());
   }
}
