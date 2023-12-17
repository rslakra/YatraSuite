package com.rslakra.microservice.yatrasuite.vehicleservice.controller;

import static com.rslakra.microservice.yatrasuite.common.Constants.ERR_INVALID_VEHICLE_ID;
import static com.rslakra.microservice.yatrasuite.framework.CommonUtils.deserialize;
import static com.rslakra.microservice.yatrasuite.framework.CommonUtils.toUUID;

import com.devamatre.appsuite.core.BeanUtils;
import com.devamatre.appsuite.core.Payload;
import com.devamatre.appsuite.spring.controller.rest.AbstractRestController;
import com.devamatre.appsuite.spring.exception.InvalidRequestException;
import com.devamatre.appsuite.spring.filter.Filter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rslakra.microservice.yatrasuite.common.Constants;
import com.rslakra.microservice.yatrasuite.common.YatraUtils;
import com.rslakra.microservice.yatrasuite.common.exception.DeserializationException;
import com.rslakra.microservice.yatrasuite.common.exception.InvalidUUIDException;
import com.rslakra.microservice.yatrasuite.common.exception.InvalidValueException;
import com.rslakra.microservice.yatrasuite.common.exception.InvalidVehicleStateException;
import com.rslakra.microservice.yatrasuite.common.exception.NotFoundException;
import com.rslakra.microservice.yatrasuite.framework.CommonUtils;
import com.rslakra.microservice.yatrasuite.framework.advice.AbstractResponse;
import com.rslakra.microservice.yatrasuite.vehicleservice.dto.NewVehicleDTO;
import com.rslakra.microservice.yatrasuite.vehicleservice.dto.VehicleCheckin;
import com.rslakra.microservice.yatrasuite.vehicleservice.dto.VehicleDetailDTO;
import com.rslakra.microservice.yatrasuite.vehicleservice.dto.VehicleIdDTO;
import com.rslakra.microservice.yatrasuite.vehicleservice.dto.VehicleWithHistoryDTO;
import com.rslakra.microservice.yatrasuite.vehicleservice.dto.VehicleWithLocationDTO;
import com.rslakra.microservice.yatrasuite.vehicleservice.kafka.event.EventType;
import com.rslakra.microservice.yatrasuite.vehicleservice.kafka.event.KafkaMessage;
import com.rslakra.microservice.yatrasuite.vehicleservice.kafka.event.RideEndedEvent;
import com.rslakra.microservice.yatrasuite.vehicleservice.kafka.event.RideStartedEvent;
import com.rslakra.microservice.yatrasuite.vehicleservice.persistence.entity.Vehicle;
import com.rslakra.microservice.yatrasuite.vehicleservice.persistence.entity.VehicleWithLocation;
import com.rslakra.microservice.yatrasuite.vehicleservice.service.VehicleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.validation.constraints.Min;

/**
 * The <code>Vehicles</code> controller manages the vehicle activities.
 *
 * @author Rohtash Lakra
 * @created 7/25/23 8:50 PM
 */
@RestController
@RequestMapping("${apiPrefix}/vehicles")
@Validated
public class VehicleController extends AbstractRestController<Vehicle, UUID> {

    private static final Logger LOGGER = LogManager.getLogger(VehicleController.class);
    private final ObjectMapper mapper;
    private final VehicleService vehicleService;

    /**
     * @param mapper
     * @param vehicleService
     */
    @Autowired
    public VehicleController(ObjectMapper mapper, VehicleService vehicleService) {
        LOGGER.debug("VehicleController({}, {})", mapper, vehicleService);
        this.mapper = mapper;
        this.vehicleService = vehicleService;
    }

    /**
     * @return
     */
    @Override
    public List<Vehicle> getAll() {
        return vehicleService.getAll();
    }

    /**
     * @param filter
     * @return
     */
    @Override
    public List<Vehicle> getByFilter(Filter filter) {
        return null;
    }

    /**
     * @param filter
     * @param pageable
     * @return
     */
    @Override
    public Page<Vehicle> getByFilter(Filter filter, Pageable pageable) {
        return null;
    }

    /**
     * @param allParams
     * @return
     */
    @GetMapping("/filter")
    @Override
    public List<Vehicle> getByFilter(@RequestParam Map<String, Object> allParams) {
        LOGGER.debug("+getByFilter({})", allParams);
        List<Vehicle> vehicles = null;
        if (BeanUtils.isEmpty(allParams)) {
            throw new InvalidRequestException("The filter criteria should provide!");
        } else if (allParams.containsKey("id")) {
            final String vehicleId = allParams.get("id").toString();
            try {
                Vehicle vehicle = vehicleService.getById(CommonUtils.toUUID(vehicleId, ERR_INVALID_VEHICLE_ID));
                vehicles = Arrays.asList(vehicle);
            } catch (InvalidUUIDException ex) {
                throw new InvalidRequestException("vehicleId:%s", vehicleId);
            }
        } else if (allParams.containsKey("email")) {
            final String userEmail = allParams.get("email").toString();
            LOGGER.debug("userEmail:{}", userEmail);
        }

//        return vehicleService.getByFilter(null);
        LOGGER.debug("-getByFilter(), vehicles: {}", vehicles);
        return vehicles;
    }

    /**
     * @param allParams
     * @param pageable
     * @return
     */
    @Override
    public Page<Vehicle> getByFilter(@RequestParam Map<String, Object> allParams, Pageable pageable) {
        return null;
    }

    /**
     * @param vehicle
     * @return
     */
    @Override
    public ResponseEntity<Vehicle> create(Vehicle vehicle) {
        return null;
    }

    /**
     * @param vehicles
     * @return
     */
    @Override
    public ResponseEntity<List<Vehicle>> create(List<Vehicle> vehicles) {
        return null;
    }

    /**
     * @param vehicle
     * @return
     */
    @Override
    public ResponseEntity<Vehicle> update(Vehicle vehicle) {
        return null;
    }

    /**
     * @param vehicles
     * @return
     */
    @Override
    public ResponseEntity<List<Vehicle>> update(List<Vehicle> vehicles) {
        return null;
    }

    /**
     * @param idOptional
     * @return
     */
    @Override
    public ResponseEntity<Payload> delete(Optional<UUID> idOptional) {
        return null;
    }

    /**
     * @param file
     * @return
     */
    @Override
    public ResponseEntity<Payload> upload(MultipartFile file) {
        return null;
    }

    /**
     * @param fileType
     * @return
     */
    @Override
    public ResponseEntity<Resource> download(String fileType) {
        return null;
    }

    /**
     * Adds a vehicle.
     *
     * @param newVehicleDTO a POJO holding the json that was passed in containing the vehicle details
     * @return the generated uuid (key) of the added vehicle
     */
    @PostMapping
    public ResponseEntity<VehicleIdDTO> addVehicle(@RequestBody NewVehicleDTO newVehicleDTO)
        throws InvalidValueException {
        LOGGER.debug("addVehicle({})", newVehicleDTO);
        LOGGER.info("[POST] /api/vehicles");
        YatraUtils.validateSerialNumber(newVehicleDTO.getSerialNumber());
        Vehicle vehicle = vehicleService.addVehicle(YatraUtils.asBigDecimalLatitude(newVehicleDTO.getLatitude()),
                                                    YatraUtils.asBigDecimalLongitude(newVehicleDTO.getLongitude()),
                                                    YatraUtils.asIntegerBatteryLevel(newVehicleDTO.getBatteryLevel()),
                                                    new VehicleDetailDTO(newVehicleDTO));
        return ResponseEntity.ok(VehicleIdDTO.fromVehicle(vehicle));
    }

    /**
     * Gets a list of vehicles with their location(limited by passed value).
     *
     * @param maxVehicles the maximum number of vehicle rows to return
     * @return a json array containing the vehicle details
     * @throws InvalidValueException if you pass 0 or a negative value for the maximum rows to return
     */
    @GetMapping
    public ResponseEntity<List<VehicleWithLocationDTO>> getVehiclesWithLocation(
        @RequestParam(value = "max_vehicles", required = false) @Min(1) Integer maxVehicles) {
        LOGGER.info("[GET] /api/vehicles");
        List<VehicleWithLocation> vehicleWithLocations = vehicleService.getVehiclesWithLocation(maxVehicles);
        return ResponseEntity.ok(VehicleUtils.toVehicleWithLocationDTOList(vehicleWithLocations));
    }

    /**
     * Gets a specific vehicle with its location history.
     *
     * @param vehicleId the uuid of the vehicle to return location history for
     * @return json with the vehicle details and a json array of all its past locations
     * @throws InvalidUUIDException if the passed vehicleId string is not a valid UUID
     * @throws NotFoundException    if the passed vehicleId is not in the database
     */
    @GetMapping("/{vehicleId}")
    public ResponseEntity<VehicleWithHistoryDTO> getVehicleLocationHistory(@PathVariable String vehicleId)
        throws InvalidUUIDException, NotFoundException {
        LOGGER.info("[GET] /api/vehicles/{}", vehicleId);
        Vehicle vehicle = vehicleService.getVehicle(toUUID(vehicleId, ERR_INVALID_VEHICLE_ID), true);
        return ResponseEntity.ok(VehicleUtils.toVehicleWithHistoryDTO(vehicle));
    }

    /**
     * Gets a specific vehicle and it's current location.
     *
     * @param vehicleId
     * @return
     */
    @GetMapping("/location/{vehicleId}")
    public ResponseEntity<VehicleWithLocationDTO> getVehicleWithLocation(@PathVariable String vehicleId)
        throws NotFoundException, InvalidUUIDException {
        LOGGER.info("[GET] /api/vehicles/location/{}", vehicleId);
        Vehicle vehicle = vehicleService.getVehicle(toUUID(vehicleId, ERR_INVALID_VEHICLE_ID), true);
        return ResponseEntity.ok(VehicleUtils.toVehicleWithLocationDto(vehicle));
    }

    /**
     * From the supplier’s perspective, check-in is an instance when the renter returns the rental vehicle to the
     * supplier at the end of the rental period. In other words, check-in meaning is the same as car rental return or
     * collection.
     * <p>
     * It is important to note that this is car rental supplier specific terminology. Check-in can have a different
     * meaning in other travel sectors.
     *
     * @param vehicleCheckin
     * @return
     * @throws NotFoundException
     */
    @PutMapping("/check-in")
    public ResponseEntity<VehicleWithLocationDTO> checkinVehicle(@RequestBody VehicleCheckin vehicleCheckin)
        throws NotFoundException {
        LOGGER.debug("+checkinVehicle({})", vehicleCheckin);
        Vehicle
            vehicle =
            vehicleService.checkinVehicle(vehicleCheckin.getVehicleId(), vehicleCheckin.getLatitude(),
                                          vehicleCheckin.getLongitude(), vehicleCheckin.getBatteryLevel(),
                                          LocalDateTime.now());
        vehicleService.fillLocationHistory(vehicle);
        ResponseEntity<VehicleWithLocationDTO>
            responseEntity =
            ResponseEntity.ok(VehicleUtils.toVehicleWithLocationDto(vehicle));
        LOGGER.debug("-checkinVehicle(), responseEntity:{}", responseEntity);
        return responseEntity;
    }

    /**
     * From the supplier’s perspective, check-out is an instance when the renter receives the rental vehicle from the
     * supplier in the beginning of the rental period. In other words, check-out meaning is the same as pick-up or
     * delivery.
     * <p>
     * It is important to note that this is car rental supplier specific terminology. Check-out can have a different
     * meaning in other travel sectors.
     *
     * @param vehicleId
     * @return
     * @throws NotFoundException
     */
    @PutMapping("/check-out/{vehicleId}")
    public ResponseEntity<VehicleWithLocationDTO> checkoutVehicle(@PathVariable Optional<UUID> vehicleId)
        throws NotFoundException, InvalidValueException {
        LOGGER.debug("-checkoutVehicle({})", vehicleId);
        if (!vehicleId.isPresent()) {
            throw new InvalidValueException("The vehicle ID should provide!");
        }

        Vehicle vehicle = vehicleService.checkoutVehicle(vehicleId.get(), LocalDateTime.now());
        vehicleService.fillLocationHistory(vehicle);
        ResponseEntity<VehicleWithLocationDTO>
            responseEntity =
            ResponseEntity.ok(VehicleUtils.toVehicleWithLocationDto(vehicle));
        LOGGER.debug("-checkoutVehicle(), responseEntity:{}", responseEntity);
        return responseEntity;
    }


    /**
     * Removes a specific vehicle.
     *
     * @param vehicleId the uuid of the vehicle to delete
     * @return "nothing"
     * @throws InvalidUUIDException if the passed vehicleId string is not a valid UUID
     * @throws NotFoundException    if the passed vehicleId is not in the database
     */
    @DeleteMapping("/{vehicleId}")
    public ResponseEntity<AbstractResponse> removeVehicle(@PathVariable String vehicleId)
        throws InvalidUUIDException, NotFoundException, InvalidVehicleStateException {
        LOGGER.info("[DELETE] /api/vehicles/{vehicleId}");
        vehicleService.removeVehicle(toUUID(vehicleId, ERR_INVALID_VEHICLE_ID));
        String response = String.format(Constants.MSG_DELETED_VEHICLE, vehicleId);
        return ResponseEntity.ok(VehicleUtils.buildResponse(response));
    }

    /**
     * Consumes messages from a Kafka Topic
     *
     * @param message The contents of the message from Kafka
     * @throws InvalidVehicleStateException If the vehicle is in a state that is invalid for the given message
     * @throws NotFoundException            If the vehicle referred to in the message doesn't exist
     */
//    @KafkaListener(topics = "yatra-suite.public.events")
    public void handleRideEvent(KafkaMessage message) throws NotFoundException, DeserializationException {
        switch (EventType.ofString(message.getMessage().getEventType())) {
            case RideStarted:
                LOGGER.debug("[KAFKA] Received {}", message.getMessage().getEventType());
                RideStartedEvent
                    rideStarted =
                    deserialize(mapper, message.getMessage().getEventData(), RideStartedEvent.class);
                vehicleService.checkoutVehicle(rideStarted.getVehicleId(), rideStarted.getStartTime());
                break;
            case RideEnded:
                LOGGER.debug("[KAFKA] Received {}", message.getMessage().getEventType());
                RideEndedEvent
                    rideEnded =
                    deserialize(mapper, message.getMessage().getEventData(), RideEndedEvent.class);
                vehicleService.checkinVehicle(rideEnded.getVehicleId(), rideEnded.getLatitude(),
                                              rideEnded.getLongitude(), rideEnded.getBattery(), rideEnded.getEndTime());
                break;
            default:
                LOGGER.warn("[KAFKA] Received an unknown message: {}", message.getMessage().getEventType());
        }
    }
}

