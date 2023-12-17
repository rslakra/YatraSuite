package com.rslakra.microservice.yatrasuite.vehicleservice.dto;

import com.rslakra.microservice.yatrasuite.vehicleservice.persistence.entity.Vehicle;
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
public class VehicleIdDTO {

    private UUID id;

    public static VehicleIdDTO fromVehicle(Vehicle vehicle) {
        VehicleIdDTO dto = new VehicleIdDTO();
        dto.setId(vehicle.getId());
        return dto;
    }

}
