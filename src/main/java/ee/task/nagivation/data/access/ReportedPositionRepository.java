package ee.task.nagivation.data.access;

import ee.task.nagivation.data.ReportedPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReportedPositionRepository extends JpaRepository<ReportedPosition, Long> {

     Iterable<ReportedPosition> findAllByMobileStationId(UUID mobileStationId);
}
