package com.rslakra.microservice.yatrawebapp.controller;

import com.rslakra.microservice.common.exception.InvalidUUIDException;
import com.rslakra.microservice.common.exception.InvalidVehicleStateException;
import com.rslakra.microservice.common.exception.NotFoundException;
import com.rslakra.microservice.yatrawebapp.clients.RidesClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * REST Controller to manage ride activities
 */

@RestController
@RequestMapping("${apiPrefix}/rides")
public class RideController {

    private final Logger LOGGER = LogManager.getLogger(this.getClass());

    private RidesClient ridesClient;

    @Autowired
    public RideController(RidesClient ridesClient) {
        this.ridesClient = ridesClient;
    }

    /**
     * Starts a ride on this vehicle for this user.
     *
     * @param startRideRequestDTO a POJO holding the json that was passed in
     * @return Json with details about the started ride
     * @throws NotFoundException            if the vehicle or user is not found
     * @throws InvalidUUIDException         if the passed vehicleId string is not a valid UUID
     * @throws InvalidVehicleStateException if the requested vehicle is not already marked "in use"
     */
    @PostMapping("/start")
    public ResponseEntity<Map<String, Object>> startRide(@RequestBody Map<String, Object> startRideRequestDTO)
        throws NotFoundException, InvalidUUIDException, InvalidVehicleStateException {
        LOGGER.info("[POST] /ui/rides/start");
        Map<String, Object> response = ridesClient.startRide(startRideRequestDTO);
        return ResponseEntity.ok(response);
    }

    /**
     * Ends this specific ride (also calculates time, distance, and speed travelled).
     *
     * @param endRideRequestDTO a POJO holding the json that was passed in
     * @return a message about the time, speed and distance travelled
     */
    @PostMapping("/end")
    public ResponseEntity<Map<String, Object>> endRide(@RequestBody @Validated Map<String, Object> endRideRequestDTO) {
        LOGGER.info("[POST] /ui/rides/end");
        Map<String, Object> response = ridesClient.endRide(endRideRequestDTO);
        return ResponseEntity.ok(response);
    }

    /**
     * Gets a list of all rides for the given user.
     *
     * @param email email of the user to get rides for
     * @return List of all the rides (active and history) for this user
     * @throws NotFoundException if the vehicle or user is not found
     */
    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getRides(@RequestParam String email) {
        LOGGER.info("[GET] /ui/rides?email={email}");
        List<Map<String, Object>> rides = ridesClient.getRides(email);
        return ResponseEntity.ok(rides);
    }
}

