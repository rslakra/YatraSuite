package com.rslakra.microservice.vehicleservice.dto;

import com.rslakra.microservice.vehicleservice.persistence.entity.Vehicle;
import com.rslakra.microservice.vehicleservice.util.VehicleTestUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehicleIdDTOTest {
    @Test
    public void fromVehicle_shouldReturnAVehicleIdDTO() {
        Vehicle vehicle = VehicleTestUtils.createVehicle();

        VehicleIdDTO dto = VehicleIdDTO.fromVehicle(vehicle);

        assertEquals(vehicle.getId(), dto.getId());
    }
}