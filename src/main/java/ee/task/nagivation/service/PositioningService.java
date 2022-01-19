package ee.task.nagivation.service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import ee.task.nagivation.config.NavigationProperties;
import ee.task.nagivation.data.BaseStation;
import ee.task.nagivation.data.MobileStation;
import ee.task.nagivation.data.ReportedPosition;
import ee.task.nagivation.data.access.BaseStationRepository;
import ee.task.nagivation.data.access.MobileStationRepository;
import ee.task.nagivation.data.access.ReportedPositionRepository;
import ee.task.nagivation.data.dto.BaseStationRequest;
import ee.task.nagivation.data.dto.MobileStationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static ee.task.nagivation.exceptions.NavigationExceptions.DISTANCE_EXCEEDS_DETECTION_RADIUS;
import static ee.task.nagivation.exceptions.NavigationExceptions.INVALID_BASE_ID;
import static ee.task.nagivation.exceptions.NavigationExceptions.MOBILE_NOT_DETECTED;
import static ee.task.nagivation.exceptions.NavigationExceptions.NEGATIVE_DISTANCE_VALUE;
import static ee.task.nagivation.util.ConversionUtil.convertToResponse;
import static ee.task.nagivation.util.FiltrationUtil.filterReports;


@Service
public class PositioningService {

   /* dependencies */

   @Autowired
   BaseStationRepository baseStationRepository;
   @Autowired
   MobileStationRepository mobileStationRepository;
   @Autowired
   ReportedPositionRepository reportedPositionRepository;
   @Autowired
   CalculationService calculationService;
   @Autowired
   NavigationProperties navigationProperties;

   /* actions */

   public void updatePosition(BaseStationRequest baseStationRequest) {
      saveNewReport(baseStationRequest);

      List<MobileStation> mobileStations = new ArrayList<>();

      Arrays.stream(baseStationRequest.getReports()).forEach(report -> {
         List<ReportedPosition> reports = reportedPositionRepository.findAllByMobileStationId(report.getMobileId());

         reports = filterReports(reports, navigationProperties.getDataExpirationTime());

         MobileStation mobileStation = calculationService.calculatePosition(reports);

         mobileStations.add(mobileStation);
      });
      mobileStationRepository.saveAll(mobileStations);
   }

   public MobileStationResponse queryPosition(UUID mobileId) {
      Iterable<MobileStation> stations = mobileStationRepository.findAll();

      Optional<MobileStation> mobileStation = mobileStationRepository.findById(mobileId);

      if (mobileStation.isPresent()) {
         return convertToResponse(mobileStation.get());
      }

      return MobileStationResponse.builder()
           .errorCode(404)
           .errorDescription(MOBILE_NOT_DETECTED)
           .build();
   }

   public Iterable<BaseStation> queryBaseStations() {
      return baseStationRepository.findAll();
   }

   /* implementation */

   private void saveNewReport(BaseStationRequest request) {
      Optional<BaseStation> baseStation = baseStationRepository.findById(request.getBaseId());

      if (baseStation.isPresent()) {
         Arrays.stream(request.getReports())
              .forEach(report -> {

                 if (report.getDistance() > baseStation.get().getDetectionRadiusInMeters()) {
                    throw new IllegalArgumentException(DISTANCE_EXCEEDS_DETECTION_RADIUS);
                 } else if (report.getDistance() < 0) {
                    throw new IllegalArgumentException(NEGATIVE_DISTANCE_VALUE);
                 }

                 reportedPositionRepository.save(ReportedPosition.builder()
                      .mobileStationId(report.getMobileId())
                      .x(baseStation.get().getX())
                      .y(baseStation.get().getY())
                      .errorRadius(report.getDistance())
                      .timestamp(report.getTimestamp())
                      .build());
              });

         return;
      }
      throw new NoSuchElementException(INVALID_BASE_ID);
   }

}
