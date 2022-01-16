package ee.task.nagivation.controller;

import ee.task.nagivation.data.dto.BaseStationRequest;
import ee.task.nagivation.data.dto.MobileStationResponse;
import ee.task.nagivation.service.PositioningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class NavigationController {

    /* dependencies */

    @Autowired
    PositioningService positioningService;

    @PostMapping
    public ResponseEntity<String> detectMobileStations(BaseStationRequest baseStationRequest) {
        try {
            positioningService.updatePosition(baseStationRequest);

            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(path = "location/{UUID}")
    public MobileStationResponse queryMobileStationPosition(@PathVariable UUID mobileId) {
        return positioningService.queryPosition(mobileId);
    }
}
