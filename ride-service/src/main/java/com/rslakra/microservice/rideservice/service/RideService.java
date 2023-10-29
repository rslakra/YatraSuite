package com.rslakra.microservice.rideservice.service;

import com.devamatre.framework.spring.service.AbstractService;
import com.rslakra.microservice.common.exception.InvalidVehicleStateException;
import com.rslakra.microservice.common.exception.NotFoundException;
import com.rslakra.microservice.rideservice.persistence.entity.Ride;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @author Rohtash Lakra
 * @created 7/23/23 9:16 PM
 */
public interface RideService extends AbstractService<Ride, Long> {

    /**
     * @param vehicleId
     * @param userEmail
     * @param startTime
     * @return
     * @throws InvalidVehicleStateException
     */
    Ride startRide(UUID vehicleId, String userEmail, LocalDateTime startTime) throws InvalidVehicleStateException;

    /**
     * @param vehicleId
     * @param userEmail
     * @param battery
     * @param latitude
     * @param longitude
     * @param endTime
     * @return
     * @throws NotFoundException
     */
    Ride endRide(UUID vehicleId, String userEmail, Integer battery, BigDecimal latitude, BigDecimal longitude,
                 LocalDateTime endTime) throws NotFoundException;

    /**
     * @param userEmail
     * @return
     */
    List<Ride> getRidesByUser(String userEmail);

    /**
     * @param userEmail
     * @param vehicleId
     * @return
     * @throws NotFoundException
     */
    Ride getActiveRides(String userEmail, UUID vehicleId) throws NotFoundException;

}
