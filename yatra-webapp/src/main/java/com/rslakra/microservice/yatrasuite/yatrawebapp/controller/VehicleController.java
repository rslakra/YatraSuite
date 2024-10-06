package com.rslakra.microservice.yatrasuite.yatrawebapp.controller;

import com.rslakra.microservice.yatrasuite.common.Constants;
import com.rslakra.microservice.yatrasuite.common.exception.InvalidUUIDException;
import com.rslakra.microservice.yatrasuite.framework.CommonUtils;
import com.rslakra.microservice.yatrasuite.yatrawebapp.clients.VehicleClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.Min;

/**
 * REST Controller to manage basic vehicle activities
 */

@RestController
@RequestMapping("${apiPrefix}/vehicles")
@Validated
public class VehicleController {

    private static final Logger LOGGER = LogManager.getLogger(VehicleController.class);
    private VehicleClient vehicleClient;

    @Autowired
    public VehicleController(VehicleClient vehicleClient) {
        LOGGER.debug("VehicleController({})", vehicleClient);
        this.vehicleClient = vehicleClient;
    }

    /**
     * Adds a vehicle.
     *
     * @param newVehicleDTO a POJO holding the json that was passed in containing the vehicle details
     * @return the generated uuid (key) of the added vehicle
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> addVehicle(@RequestBody Map<String, Object> newVehicleDTO) {
        LOGGER.info("[POST] /ui/vehicles");
        Map<String, Object> response = vehicleClient.addVehicle(newVehicleDTO);
        return ResponseEntity.ok(response);
    }

    /**
     * Gets a list of vehicles with their location(limited by passed value).
     *
     * @param maxVehicles the maximum number of vehicle rows to return
     * @return a json array containing the vehicle details
     */
    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getVehiclesWithLocation(
        @RequestParam(value = "max_vehicles", required = false) @Min(1) Integer maxVehicles) {
        LOGGER.info("[GET] /ui/vehicles");
        List<Map<String, Object>> vehiclesWithLocations = vehicleClient.getVehiclesWithLocation(maxVehicles);
        return ResponseEntity.ok(vehiclesWithLocations);
    }

    /**
     * @param vehicleId
     * @return
     * @throws InvalidUUIDException
     */
    @GetMapping("/location/{vehicleId}")
    public ResponseEntity<Map<String, Object>> getVehicleWithLocation(@PathVariable String vehicleId)
        throws InvalidUUIDException {
        LOGGER.info("[GET] /ui/vehicles/location/{}", vehicleId);
        Map<String, Object> vehicleWithLocation = vehicleClient.getVehicleWithLocation(
            CommonUtils.toUUID(vehicleId, Constants.ERR_INVALID_VEHICLE_ID));
        return ResponseEntity.ok(vehicleWithLocation);
    }

    /**
     * Gets a specific vehicle with its location history.
     *
     * @param vehicleId the uuid of the vehicle to return location history for
     * @return json with the vehicle details and a json array of all its past locations
     * @throws InvalidUUIDException if the passed vehicleId string is not a valid UUID
     */
    @GetMapping("/{vehicleId}")
    public ResponseEntity<Map<String, Object>> getVehicleWithHistory(@PathVariable String vehicleId)
        throws InvalidUUIDException {
        LOGGER.info("[GET] /ui/vehicles/{}", vehicleId);
        Map<String, Object>
            vehicle =
            vehicleClient.getVehicleWithHistory(CommonUtils.toUUID(vehicleId, Constants.ERR_INVALID_VEHICLE_ID));
        return ResponseEntity.ok(vehicle);
    }

    /**
     * Removes a specific vehicle.
     *
     * @param vehicleId the uuid of the vehicle to delete
     * @return "nothing"
     * @throws InvalidUUIDException if the passed vehicleId string is not a valid UUID
     */
    @DeleteMapping("/{vehicleId}")
    public ResponseEntity<Map<String, Object>> removeVehicle(@PathVariable String vehicleId)
        throws InvalidUUIDException {
        LOGGER.info("[DELETE] /ui/vehicles/{vehicleId}");
        Map<String, Object> messages = vehicleClient.removeVehicle(
            CommonUtils.toUUID(vehicleId, Constants.ERR_INVALID_VEHICLE_ID));
        return ResponseEntity.ok(messages);
    }

}

