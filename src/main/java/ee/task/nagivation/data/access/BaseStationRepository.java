package ee.task.nagivation.data.access;

import ee.task.nagivation.data.BaseStation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BaseStationRepository extends CrudRepository<BaseStation, UUID> {

}
