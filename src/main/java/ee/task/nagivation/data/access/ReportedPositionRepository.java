package ee.task.nagivation.data.access;


import java.util.List;
import java.util.UUID;

import ee.task.nagivation.data.ReportedPosition;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportedPositionRepository extends CrudRepository<ReportedPosition, Long> {

     List<ReportedPosition> findAllByMobileStationId(UUID mobileStationId);

}
