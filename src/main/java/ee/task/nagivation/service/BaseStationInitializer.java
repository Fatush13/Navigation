package ee.task.nagivation.service;

import ee.task.nagivation.configuration.NavigationProperties;
import ee.task.nagivation.data.access.BaseStationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BaseStationInitializer {

    /* dependencies */

    @Autowired
    BaseStationRepository baseStationRepository;
    @Autowired
    NavigationProperties navigationProperties;

    /* actions */

    public void initializeBaseStations() {
        log.error("\nRaw output: {}", navigationProperties.getBaseStations());

        log.error("\nTTL: {}", navigationProperties.getDataExpirationTime().getSeconds());

        baseStationRepository.saveAll(navigationProperties.getBaseStations());

        log.error("\nStations from repo: {}", baseStationRepository.findAll());
    }

}
