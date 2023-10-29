package com.rslakra.microservice.yatrathymeleaf.dto.vehicle;

import com.rslakra.frameworks.spring.payload.dto.AbstractEntityDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/**
 * @author Rohtash Lakra
 * @created 7/25/23 8:50 PM
 */
@Getter
@Setter
@NoArgsConstructor
public class VehicleId extends AbstractEntityDTO<UUID> {

    /**
     * @param vehicle
     * @return
     */
    public static VehicleId fromVehicle(Vehicle vehicle) {
        VehicleId vehicleId = new VehicleId();
        vehicleId.setId(vehicle.getId());
        return vehicleId;
    }

}
