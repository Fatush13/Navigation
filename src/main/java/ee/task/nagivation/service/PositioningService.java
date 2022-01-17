package ee.task.nagivation.service;


import ee.task.nagivation.configuration.NavigationProperties;
import ee.task.nagivation.data.BaseStation;
import ee.task.nagivation.data.MobileStation;
import ee.task.nagivation.data.ReportedPosition;
import ee.task.nagivation.data.access.BaseStationRepository;
import ee.task.nagivation.data.access.MobileStationRepository;
import ee.task.nagivation.data.access.ReportedPositionRepository;
import ee.task.nagivation.data.dto.BaseStationRequest;
import ee.task.nagivation.data.dto.MobileStationResponse;
import ee.task.nagivation.util.CompareUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static ee.task.nagivation.exceptions.NavigationExceptions.*;
import static ee.task.nagivation.util.ConvertUtil.convertToResponse;


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
      BaseStation baseStation = saveNewReport(baseStationRequest);

      List<MobileStation> mobileStations = new ArrayList<>();

      Arrays.stream(baseStationRequest.getReports()).forEach(report -> {
         List<ReportedPosition> reports = reportedPositionRepository.findAllByMobileStationId(report.getMobileId());

         ReportedPosition latestReport = ReportedPosition.builder()
              .x(baseStation.getX())
              .y(baseStation.getY())
              .errorRadius(report.getDistance())
              .build();

         reports = filterRelevantReports(reports, latestReport);

         MobileStation mobileStation = calculationService.calculatePosition(reports);

         mobileStations.add(mobileStation);
      });
      mobileStationRepository.saveAll(mobileStations);
   }

   public MobileStationResponse queryPosition(UUID mobileId) {
      Optional<MobileStation> mobileStation = mobileStationRepository.findById(mobileId);

      if (mobileStation.isPresent()) {
         return convertToResponse(mobileStation.get());
      }
      throw new NoSuchElementException(NOT_DETECTED);
   }

   /* implementation */

   private BaseStation saveNewReport(BaseStationRequest request) {
      Optional<BaseStation> baseStation = baseStationRepository.findById(request.getBaseId());

      if (baseStation.isPresent()) {
         Arrays.stream(request.getReports())
              .forEach(report -> reportedPositionRepository.save(ReportedPosition.builder()
                   .mobileStationId(report.getMobileId())
                   .x(baseStation.get().getX())
                   .y(baseStation.get().getY())
                   .errorRadius(report.getDistance())
                   .build()));

         return baseStation.get();
      }
      throw new NoSuchElementException(INVALID_BASE_STATION_ID);
   }

   private List<ReportedPosition> filterRelevantReports(List<ReportedPosition> reports,
        ReportedPosition latestReport)
   {
      LocalDateTime latestReportTime = latestReport.getTimestamp();

      List<ReportedPosition> filteredByTime = filterByTime(reports, latestReportTime);

      return filterByPosition(filteredByTime, latestReport);
   }

   private List<ReportedPosition> filterByTime(
        List<ReportedPosition> reports, LocalDateTime latestReportTime)
   {               // filters out reports by expiration time
      return reports.stream()
           .filter(report -> report.getTimestamp()
                .isBefore(latestReportTime.minus(navigationProperties.getDataExpirationTime())))
           .collect(Collectors.toList());
   }

   private List<ReportedPosition> filterByPosition(List<ReportedPosition> reports, ReportedPosition latestReport)
   {          //  filters out reported position that do not overlap with the latest position
      return reports.stream()
           .filter(report -> CompareUtil.isOverLapping(latestReport, report))
           .collect(Collectors.toList());
   }

}
