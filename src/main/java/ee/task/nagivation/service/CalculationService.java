package ee.task.nagivation.service;


import java.util.List;

import lombok.extern.slf4j.Slf4j;

import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;

import ee.task.nagivation.data.MobileStation;
import ee.task.nagivation.data.ReportedPosition;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.apache.commons.math3.linear.RealVector;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class CalculationService {

   public MobileStation calculatePosition(List<ReportedPosition> reports) {
      if (reports.size() < 2) {
         return calculateBySingleStation(reports.get(0));
      }
      return calculateByMultipleStations(reports);
   }

   private MobileStation calculateBySingleStation(ReportedPosition reportedPosition) {
      return MobileStation.builder()
           .id(reportedPosition.getMobileStationId())
           .lastKnownX(reportedPosition.getX())
           .lastKnownY(reportedPosition.getY())
           .errorRadius(reportedPosition.getErrorRadius())
           .build();
   }

   private MobileStation calculateByMultipleStations(List<ReportedPosition> reports) {
      NonLinearLeastSquaresSolver solver = initializeSolver(reports);

      LeastSquaresOptimizer.Optimum optimum = solver.solve();

      RealVector centroid = optimum.getPoint();

      double deviation = defineDeviation(optimum);

      return MobileStation.builder()
           .id(reports.get(0).getMobileStationId())
           .lastKnownX((float) centroid.getEntry(0))
           .lastKnownY((float) centroid.getEntry(1))
           .errorRadius((float) deviation)
           .build();
   }

   private NonLinearLeastSquaresSolver initializeSolver(List<ReportedPosition> reports) {
      double[][] positions = new double[reports.size()][2];
      double[] distances = new double[reports.size()];
      int i = 0;

      for (ReportedPosition report : reports) {
         positions[i][0] = report.getX();
         positions[i][1] = report.getY();

         distances[i] = report.getErrorRadius();

         i++;
      }

      return new NonLinearLeastSquaresSolver(
           new TrilaterationFunction(positions, distances), new LevenbergMarquardtOptimizer());
   }

   private double defineDeviation(LeastSquaresOptimizer.Optimum optimum) {
      RealVector vector = optimum.getSigma(0);

      return vector.getMaxValue() * 2;    // multiplier is a workaround to compensate multiple intersections problem
   }

}
