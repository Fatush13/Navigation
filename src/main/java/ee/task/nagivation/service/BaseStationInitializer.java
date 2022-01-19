package ee.task.nagivation.service;


import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

import ee.task.nagivation.config.NavigationProperties;
import ee.task.nagivation.data.BaseStation;
import ee.task.nagivation.data.access.BaseStationRepository;
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
      List<BaseStation> baseStations = navigationProperties.getBaseStations().stream()
           .filter(station -> station.getDetectionRadiusInMeters() > 0)
           .collect(Collectors.toList());

      baseStationRepository.saveAll(baseStations);
   }

}
