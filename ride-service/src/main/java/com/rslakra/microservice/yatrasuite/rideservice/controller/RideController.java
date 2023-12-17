package com.rslakra.microservice.yatrasuite.rideservice.controller;

import com.devamatre.appsuite.core.BeanUtils;
import com.devamatre.appsuite.core.Payload;
import com.devamatre.appsuite.spring.controller.rest.AbstractRestController;
import com.devamatre.appsuite.spring.exception.InvalidRequestException;
import com.devamatre.appsuite.spring.filter.Filter;
import com.rslakra.microservice.yatrasuite.common.Constants;
import com.rslakra.microservice.yatrasuite.common.YatraUtils;
import com.rslakra.microservice.yatrasuite.common.exception.InvalidUUIDException;
import com.rslakra.microservice.yatrasuite.common.exception.InvalidValueException;
import com.rslakra.microservice.yatrasuite.common.exception.InvalidVehicleStateException;
import com.rslakra.microservice.yatrasuite.common.exception.NotFoundException;
import com.rslakra.microservice.yatrasuite.rideservice.dto.EndRideRequestDto;
import com.rslakra.microservice.yatrasuite.rideservice.dto.RideDto;
import com.rslakra.microservice.yatrasuite.rideservice.dto.RideRequestDto;
import com.rslakra.microservice.yatrasuite.rideservice.dto.RideResponseDto;
import com.rslakra.microservice.yatrasuite.rideservice.filter.RideFilter;
import com.rslakra.microservice.yatrasuite.rideservice.persistence.entity.Ride;
import com.rslakra.microservice.yatrasuite.rideservice.service.RideService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Manages the ride activities.
 *
 * @author Rohtash Lakra
 * @created 7/23/23 9:16 PM
 */
@RestController
@RequestMapping("${apiPrefix}/rides")
public class RideController extends AbstractRestController<Ride, Long> {

    private static final Logger LOGGER = LogManager.getLogger(RideController.class);
    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    private RideService rideService;

    @Autowired
    public RideController(RideService rideService) {
        this.rideService = rideService;
    }

    /**
     * @return
     */
    @Override
    public List<Ride> getAll() {
        return rideService.getAll();
    }

    /**
     * @param filter
     * @return
     */
    @Override
    public List<Ride> getByFilter(Filter filter) {
        return rideService.getByFilter(filter);
    }

    /**
     * @param filter
     * @param pageable
     * @return
     */
    @Override
    public Page<Ride> getByFilter(Filter filter, Pageable pageable) {
        return rideService.getByFilter(filter, pageable);
    }

    /**
     * @param allParams
     * @return
     */
    @GetMapping("/filter")
    @Override
    public List<Ride> getByFilter(@RequestParam Map<String, Object> allParams) {
        return getByFilter(new RideFilter(allParams));
    }

    /**
     * @param allParams
     * @param pageable
     * @return
     */
    @GetMapping("/filter-pageable")
    @Override
    public Page<Ride> getByFilter(Map<String, Object> allParams, Pageable pageable) {
        return rideService.getByFilter(new RideFilter(allParams), pageable);
    }

    /**
     * @param ride
     * @return
     */
    @Override
    public ResponseEntity<Ride> create(Ride ride) {
        return null;
    }

    /**
     * @param rides
     * @return
     */
    @Override
    public ResponseEntity<List<Ride>> create(List<Ride> rides) {
        return null;
    }

    /**
     * @param ride
     * @return
     */
    @Override
    public ResponseEntity<Ride> update(Ride ride) {
        return null;
    }

    /**
     * @param rides
     * @return
     */
    @Override
    public ResponseEntity<List<Ride>> update(List<Ride> rides) {
        return null;
    }

    /**
     * @param idOptional
     * @return
     */
    @Override
    public ResponseEntity<Payload> delete(Optional<Long> idOptional) {
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
     * Gets the active ride for this vehicle/user combination.
     *
     * @param vehicleId the vehicle that the user is riding
     * @param email     the email address that identifies the user
     * @return Json containing details about the ride
     * @throws NotFoundException    if the vehicle or user is not found
     * @throws InvalidUUIDException if the passed vehicleId string is not a valid UUID
     */
    @GetMapping("/active")
    public ResponseEntity<RideDto> getActiveRide(@RequestParam("vehicle_id") String vehicleId,
                                                 @RequestParam String email)
        throws NotFoundException, InvalidUUIDException {
        LOGGER.info("[GET] /api/rides/active?email=" + email + "&vehicle_id=" + vehicleId);
        Ride ride = rideService.getActiveRides(email, UUID.fromString(vehicleId));
        return ResponseEntity.ok(toDto(ride));
    }

    /**
     * Starts a ride on this vehicle for this user.
     *
     * @param rideRequest a POJO holding the json that was passed in
     * @return Json with details about the started ride
     * @throws InvalidUUIDException if the passed vehicleId string is not a valid UUID
     */
    @PostMapping("/start")
    public ResponseEntity<RideResponseDto> startRide(@RequestBody RideRequestDto rideRequest)
        throws InvalidUUIDException, InvalidVehicleStateException {
        LOGGER.info("[POST] /api/rides/start");

        if (BeanUtils.isEmpty(rideRequest.getEmail())) {
            throw new InvalidRequestException("The email should provide!");
        } else if (BeanUtils.isEmpty(rideRequest.getVehicleId())) {
            throw new InvalidRequestException("The vehicleId should provide!");
        }

        LocalDateTime startTime = LocalDateTime.now(ZoneOffset.UTC);
        Ride
            ride =
            rideService.startRide(UUID.fromString(rideRequest.getVehicleId()), rideRequest.getEmail(), startTime);
        RideDto rideDTO = toDto(ride);
        RideResponseDto rideResponseDTO = new RideResponseDto();
        rideResponseDTO.setRideDTO(rideDTO);
        rideResponseDTO.setMessages(
            new String[]{String.format(Constants.MSG_RIDE_STARTED, rideRequest.getVehicleId())});
        return ResponseEntity.ok(rideResponseDTO);
    }

    /**
     * Ends this specific ride (also calculates time, distance, and speed travelled).
     *
     * @param endRideRequest a POJO holding the json that was passed in
     * @return a message about the time, speed and distance travelled
     * @throws NotFoundException     if the vehicle or user is not found
     * @throws InvalidUUIDException  if the passed vehicleId string is not a valid UUID
     * @throws InvalidValueException if the math calculations result in an error
     */
    @PostMapping("/end")
    public ResponseEntity<RideResponseDto> endRide(@RequestBody @Validated EndRideRequestDto endRideRequest)
        throws NotFoundException, InvalidUUIDException, InvalidValueException {

        LOGGER.info("[POST] /api/rides/end");
        if (BeanUtils.isEmpty(endRideRequest.getEmail())) {
            throw new InvalidRequestException("The email should provide!");
        } else if (BeanUtils.isEmpty(endRideRequest.getVehicleId())) {
            throw new InvalidRequestException("The vehicleId should provide!");
        }

        LocalDateTime endTime = LocalDateTime.now(ZoneOffset.UTC);
        Ride ride = rideService.endRide(UUID.fromString(endRideRequest.getVehicleId()),
                                        endRideRequest.getEmail(),
                                        YatraUtils.asIntegerBatteryLevel(endRideRequest.getBattery()),
                                        YatraUtils.asBigDecimalLatitude(endRideRequest.getLatitude()),
                                        YatraUtils.asBigDecimalLongitude(endRideRequest.getLongitude()),
                                        endTime);

        RideDto rideDTO = toDto(ride);
        RideResponseDto rideResponseDTO = new RideResponseDto();
        rideResponseDTO.setRideDTO(rideDTO);
        rideResponseDTO.setMessages(
            new String[]{String.format(Constants.MSG_RIDE_ENDED, endRideRequest.getVehicleId())});
        return ResponseEntity.ok(rideResponseDTO);
    }

    /**
     * Gets a list of all rides for the given user.
     *
     * @param email email of the user to get rides for
     * @return List of all the rides (active and history) for this user
     * @throws NotFoundException if the vehicle or user is not found
     */
    @GetMapping
    public ResponseEntity<List<RideDto>> getRides(@RequestParam String email) throws NotFoundException {
        LOGGER.info("[GET] /api/rides?email={}", email);
        List<RideDto> rideDtoList = rideService.getRidesByUser(email)
            .stream()
            .map(this::toDto)
            .collect(Collectors.toList());
        return ResponseEntity.ok(rideDtoList);
    }

    /**
     * Converts the Ride entity object to a Data Transfer Object.
     *
     * @param ride the Ride entity object
     * @return RideDto
     */
    private RideDto toDto(Ride ride) {
        return MODEL_MAPPER.map(ride, RideDto.class);
    }
}

