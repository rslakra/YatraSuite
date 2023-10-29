package com.rslakra.microservice.vehicleservice.service;

import com.devamatre.framework.spring.service.AbstractService;
import com.rslakra.microservice.common.exception.InvalidVehicleStateException;
import com.rslakra.microservice.common.exception.NotFoundException;
import com.rslakra.microservice.vehicleservice.dto.VehicleDetailDTO;
import com.rslakra.microservice.vehicleservice.persistence.entity.LocationHistory;
import com.rslakra.microservice.vehicleservice.persistence.entity.Vehicle;
import com.rslakra.microservice.vehicleservice.persistence.entity.VehicleWithLocation;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Service to handle basic CRUD functions for vehicles
 */
public interface VehicleService extends AbstractService<Vehicle, UUID> {

    Integer MAX_VEHICLES_TO_RETURN = 20;  // default LIMIT when querying

    Vehicle addVehicle(BigDecimal latitude, BigDecimal longitude, int batteryLevel, VehicleDetailDTO vehicleInfo);

    public List<VehicleWithLocation> getVehiclesWithLocation(Integer maxVehicles);

    List<Vehicle> getVehicles(Integer maxRecords);

    List<LocationHistory> getLocationHistory(UUID vehicleId);

    void fillLocationHistory(Vehicle vehicle);

    Vehicle getVehicle(UUID vehicleId, Boolean withLocationHistory) throws NotFoundException;

    Vehicle getVehicle(UUID vehicleId) throws NotFoundException;

    Vehicle checkinVehicle(UUID vehicleId, BigDecimal latitude, BigDecimal longitude, Integer batteryLevel,
                           LocalDateTime endTime)
        throws NotFoundException;

    Vehicle checkoutVehicle(UUID vehicleId, LocalDateTime leftAt) throws NotFoundException;

    void removeVehicle(UUID vehicleId) throws NotFoundException, InvalidVehicleStateException;
}
