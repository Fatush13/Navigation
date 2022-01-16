package ee.task.nagivation.service;

import ee.task.nagivation.configuration.NavigationProperties;
import ee.task.nagivation.data.MobileStation;
import ee.task.nagivation.data.ReportedPosition;
import ee.task.nagivation.data.access.BaseStationRepository;
import ee.task.nagivation.data.access.MobileStationRepository;
import ee.task.nagivation.data.access.ReportedPositionRepository;
import ee.task.nagivation.data.dto.BaseStationRequest;
import ee.task.nagivation.data.dto.MobileStationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static ee.task.nagivation.data.dto.BaseStationRequest.Report;

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
    NavigationProperties navigationProperties;

    /* actions */

    public void updatePosition(BaseStationRequest baseStationRequest) {

    }

    public MobileStationResponse queryPosition (UUID mobileId) {
        return null;
    }

    /* implementation */

    private MobileStation calculatePosition(Report report) {


        return null;
    }

    private MobileStation calculateBySingleStation(ReportedPosition reportedPosition) {
        return MobileStation.builder()
                .id(reportedPosition.getMobileStationId())
                .lastKnownX(reportedPosition.getX())
                .lastKnownY(reportedPosition.getY())
                .errorRadius(reportedPosition.getErrorRadius())
                .build();
    }

    private MobileStation calculateByMultipleStations() {
        return null;
    }

    private MobileStationResponse convertToResponse(MobileStation mobileStation) {
        return null;
    }
}
