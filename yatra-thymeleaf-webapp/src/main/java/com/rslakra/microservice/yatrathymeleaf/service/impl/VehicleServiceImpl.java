package com.rslakra.microservice.yatrathymeleaf.service.impl;

import com.rslakra.frameworks.core.BeanUtils;
import com.rslakra.frameworks.core.Payload;
import com.rslakra.frameworks.spring.client.ApiRestClient;
import com.rslakra.frameworks.spring.exception.InvalidRequestException;
import com.rslakra.frameworks.spring.persistence.Operation;
import com.rslakra.microservice.yatrathymeleaf.config.VehicleServiceConfig;
import com.rslakra.microservice.yatrathymeleaf.dto.vehicle.Vehicle;
import com.rslakra.microservice.yatrathymeleaf.framework.ui.service.impl.AbstractClientServiceImpl;
import com.rslakra.microservice.yatrathymeleaf.service.VehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author Rohtash Lakra
 * @created 10/9/21 5:50 PM
 */
@Service
public class VehicleServiceImpl extends AbstractClientServiceImpl<Vehicle, UUID> implements VehicleService {

    // apiRestClient
    private static final Logger LOGGER = LoggerFactory.getLogger(VehicleServiceImpl.class);

    private final VehicleServiceConfig serviceConfig;

    /**
     * @param serviceConfig
     */
    @Autowired
    public VehicleServiceImpl(final VehicleServiceConfig serviceConfig) {
        super(new ApiRestClient(serviceConfig.getApiUrl(null)));
        LOGGER.debug("VehicleServiceImpl({})", serviceConfig);
        this.serviceConfig = serviceConfig;
    }

    /**
     * Validates the <code>T</code> object.
     *
     * @param operation
     * @param vehicle
     * @return
     */
    @Override
    public Vehicle validate(Operation operation, Vehicle vehicle) {
        LOGGER.debug("+validate({}, {})", operation, vehicle);

        switch (operation) {
            case CREATE:
                if (BeanUtils.isEmpty(vehicle.getSerialNumber())) {
                    throw new InvalidRequestException("The vehicle's serial number should provide!");
                }

                break;

            case UPDATE:
                if (BeanUtils.isNull(vehicle.getId())) {
                    throw new InvalidRequestException("The vehicle's ID should provide!");
                }

                break;

            default:
                throw new InvalidRequestException("Unsupported Operation!");
        }

        LOGGER.debug("-validate(), vehicle:{}", vehicle);
        return vehicle;
    }

    /**
     * @param vehicle
     * @return
     */
    @Override
    public Vehicle create(Vehicle vehicle) {
        LOGGER.debug("+create({})", vehicle);
        if (BeanUtils.isNull(vehicle)) {
            throw new InvalidRequestException("The vehicle should provide!");
        }

        validate(Operation.CREATE, vehicle);
        vehicle = getRestClient().doPost(VEHICLES, vehicle, Vehicle.class);
        LOGGER.debug("-create(), vehicle:{}", vehicle);
        return vehicle;
    }

    /**
     * @param vehicles
     * @return
     */
    @Override
    public List<Vehicle> create(List<Vehicle> vehicles) {
        LOGGER.debug("+create({})", vehicles);
        if (BeanUtils.isEmpty(vehicles)) {
            throw new InvalidRequestException("The users should provide!");
        }

        vehicles.forEach(vehicle -> validate(Operation.CREATE, vehicle));
        vehicles = getRestClient().doPost(VEHICLES_BATCH, vehicles, List.class);
        LOGGER.debug("-create(), vehicles:{}", vehicles);
        return vehicles;
    }

    /**
     * @return
     */
    @Override
    public List<Vehicle> getAll() {
        LOGGER.debug("+getAll()");
        // note: get results by array and convert to list.
        List<Vehicle> vehicles;
        // helps to display empty ui page.
        try {
            vehicles = Arrays.asList(getRestClient().doGet(VEHICLES, Vehicle[].class));
        } catch (Exception ex) {
            LOGGER.error(ex.getLocalizedMessage(), ex);
            // helps to display empty ui page.
            vehicles = new ArrayList<>();
        }

        LOGGER.debug("-getAll(), vehicles:{}", vehicles);
        return vehicles;
    }

    /**
     * @param filters
     * @return
     */
    @Override
    public List<Vehicle> getByFilter(Map<String, Object> filters) {
        LOGGER.debug("+getByFilter({})", filters);
        // rest/vehicles/filter?id=3
        final Vehicle[] vehicles = getRestClient().doGet(VEHICLES_FILTER, Vehicle[].class, filters);
        LOGGER.debug("-getByFilter(), vehicles:{}", vehicles);
        return Arrays.asList(vehicles);
    }

    /**
     * @param filters
     * @param pageable
     * @return
     */
    @Override
    public List<Vehicle> getByFilter(Map<String, Object> filters, Pageable pageable) {
        LOGGER.debug("+getByFilter({}, {})", filters, pageable);
        List<Vehicle> vehicles = getByFilter(filters);
        LOGGER.debug("-getByFilter(), vehicles:{}", vehicles);
        return vehicles;
    }

    /**
     * Returns the <code>T</code> object by <code>id</code>.
     *
     * @param id
     * @return
     */
    @Override
    public Vehicle getById(UUID id) {
        LOGGER.debug("+getById({})", id);
        // rest/vehicles/filter?id=16
//        Vehicle
//            vehicle =
//            (Vehicle) getByFilter(Payload.newBuilder().ofPair("id", id)).stream().findFirst().orElse(null);

        final Vehicle
            vehicle =
            getRestClient().doGet(VEHICLE_BY_ID, Vehicle.class, Payload.newBuilder().ofPair("id", id));
        LOGGER.debug("-getById(), vehicle:{}", vehicle);
        return vehicle;
    }

    /**
     * @param vehicle
     * @return
     */
    @Override
    public Vehicle update(Vehicle vehicle) {
        LOGGER.debug("+update({})", vehicle);
        if (BeanUtils.isEmpty(vehicle)) {
            throw new InvalidRequestException("The vehicle should provide!");
        }

        validate(Operation.UPDATE, vehicle);
        getRestClient().doPut(VEHICLES, vehicle, Vehicle.class);

        LOGGER.debug("-update(), vehicle:{}", vehicle);
        return vehicle;
    }

    /**
     * @param vehicles
     * @return
     */
    @Override
    public List<Vehicle> update(List<Vehicle> vehicles) {
        LOGGER.debug("+update({})", vehicles);
        if (BeanUtils.isEmpty(vehicles)) {
            throw new InvalidRequestException("The vehicles should provide!");
        }

        vehicles.forEach(vehicle -> validate(Operation.UPDATE, vehicle));
        getRestClient().doPut(VEHICLES_BATCH, vehicles, List.class);

        LOGGER.debug("-update(), vehicles:{}", vehicles);
        return vehicles;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Vehicle delete(UUID id) {
        LOGGER.debug("+delete({})", id);
        BeanUtils.assertNonNull(id, "The vehicle's id should provide!");
        Vehicle vehicle = null;
        getRestClient().doDelete(VEHICLE_BY_ID, Payload.newBuilder().ofPair("id", id));
        LOGGER.debug("-delete(), vehicle:{}", vehicle);
        return vehicle;
    }

    /**
     * Returns the vehicle by name.
     *
     * @param name
     * @return
     */
    @Override
    public Vehicle getByName(String name) {
        LOGGER.debug("+getByName({})", name);
        BeanUtils.assertNonNull(name, "The vehicle name should provide!");
        Vehicle
            vehicle =
            (Vehicle) getByFilter(Payload.newBuilder().ofPair("name", name))
                .stream().findFirst().orElse(null);
        LOGGER.debug("-getByName(), vehicle:{}", vehicle);
        return vehicle;
    }

    /**
     * Returns the list of vehicles by status.
     *
     * @param status
     * @return
     */
    @Override
    public List<Vehicle> getByStatus(String status) {
        LOGGER.debug("+getByStatus({})", status);
        BeanUtils.assertNonNull(status, "The vehicle status should provide!");
        List<Vehicle> vehicles = getByFilter(Payload.newBuilder().ofPair("status", status));
        LOGGER.debug("-getByStatus(), vehicles: {}", vehicles);
        return vehicles;
    }
}
