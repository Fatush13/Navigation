package ee.task.nagivation.data.access;


import java.util.UUID;

import ee.task.nagivation.data.BaseStation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseStationRepository extends CrudRepository<BaseStation, UUID> {

}
