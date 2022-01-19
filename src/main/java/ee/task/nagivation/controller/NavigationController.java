package ee.task.nagivation.controller;


import java.util.UUID;

import javax.validation.Valid;

import ee.task.nagivation.data.BaseStation;
import ee.task.nagivation.data.dto.BaseStationRequest;
import ee.task.nagivation.data.dto.MobileStationResponse;
import ee.task.nagivation.service.PositioningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class NavigationController {

   /* dependencies */

   @Autowired
   PositioningService positioningService;

   /* endpoints */

   @PostMapping
   public ResponseEntity<String> detectMobileStations(@Valid @RequestBody BaseStationRequest baseStationRequest) {
      try {
         positioningService.updatePosition(baseStationRequest);

         return ResponseEntity.ok().build();
      } catch (Exception e) {
         return ResponseEntity.badRequest().body(e.getMessage());
      }
   }

   @GetMapping (path = "location/{UUID}")
   public MobileStationResponse queryMobileStationPosition(@PathVariable (name = "UUID") UUID mobileId) {
      return positioningService.queryPosition(mobileId);
   }

   @GetMapping
   public Iterable<BaseStation> queryMobileStationPosition() {
      return positioningService.queryBaseStations();
   }
}
