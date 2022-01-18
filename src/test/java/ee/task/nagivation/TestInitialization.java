package ee.task.nagivation;

import ee.task.nagivation.config.NavigationProperties;
import ee.task.nagivation.data.BaseStation;
import ee.task.nagivation.data.access.BaseStationRepository;
import ee.task.nagivation.service.BaseStationInitializer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestInitialization extends Assertions {

    @Autowired
    BaseStationInitializer baseStationInitializer;
    @Autowired
    BaseStationRepository baseStationRepository;
    @Autowired
    NavigationProperties navigationProperties;

    @Test
    void initializeTest() {
        baseStationInitializer.initializeBaseStations();

        assertEquals(5, ((java.util.Collection<BaseStation>) baseStationRepository.findAll()).size());
        assertEquals(3, navigationProperties.getDataExpirationTime().getSeconds());
    }

}
