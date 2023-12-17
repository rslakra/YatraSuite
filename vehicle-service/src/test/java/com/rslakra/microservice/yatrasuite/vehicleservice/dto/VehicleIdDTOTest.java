package com.rslakra.microservice.yatrasuite.vehicleservice.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.rslakra.microservice.yatrasuite.vehicleservice.persistence.entity.Vehicle;
import com.rslakra.microservice.yatrasuite.vehicleservice.util.VehicleTestUtils;
import org.junit.jupiter.api.Test;

class VehicleIdDTOTest {

    @Test
    public void fromVehicle_shouldReturnAVehicleIdDTO() {
        Vehicle vehicle = VehicleTestUtils.createVehicle();

        VehicleIdDTO dto = VehicleIdDTO.fromVehicle(vehicle);

        assertEquals(vehicle.getId(), dto.getId());
    }
}