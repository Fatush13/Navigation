package ee.task.nagivation;

import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.apache.commons.math3.linear.RealVector;
import org.junit.jupiter.api.Test;

import static org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer.Optimum;

@Slf4j
public class TestPositioningService {

    @Test
    void test() {
        double[][] positions = new double[][] { { 4.0, 3.0 }, { 8.0, 2.0 }, { 7.0, 5.0 }};
        double[] distances = new double[] { 3.0, 2.0, 2.0};

        NonLinearLeastSquaresSolver solver = new NonLinearLeastSquaresSolver(new TrilaterationFunction(positions, distances), new LevenbergMarquardtOptimizer());
        Optimum optimum = solver.solve();

// the answer
        RealVector centroid = optimum.getPoint();

        log.error("Centroid: {}", centroid);

// error and geometry information; may throw SingularMatrixException depending on the threshold argument provided
        RealVector standardDeviation = optimum.getSigma(1);

        log.error("Deviation by 2-axis: {}", standardDeviation);



        log.error("Deviation: {}", standardDeviation.getMaxValue());


    }
}
