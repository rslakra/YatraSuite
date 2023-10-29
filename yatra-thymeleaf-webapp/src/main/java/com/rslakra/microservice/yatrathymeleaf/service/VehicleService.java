package com.rslakra.microservice.yatrathymeleaf.service;

import com.rslakra.microservice.yatrathymeleaf.dto.vehicle.Vehicle;
import com.rslakra.microservice.yatrathymeleaf.framework.ui.service.AbstractClientService;

import java.util.List;
import java.util.UUID;

/**
 * @author Rohtash Lakra
 * @created 8/20/21 8:11 PM
 */
public interface VehicleService extends AbstractClientService<Vehicle, UUID> {

    String VEHICLES = "vehicles";
    String VEHICLES_BATCH = "vehicles/batch";
    String VEHICLE_BY_ID = "vehicles/{id}";
    String VEHICLES_FILTER = "vehicles/filter";

    /**
     * Returns the vehicle by name.
     *
     * @param name
     * @return
     */
    public Vehicle getByName(String name);

    /**
     * Returns the list of vehicles by status.
     *
     * @param status
     * @return
     */
    public List<Vehicle> getByStatus(String status);

}
