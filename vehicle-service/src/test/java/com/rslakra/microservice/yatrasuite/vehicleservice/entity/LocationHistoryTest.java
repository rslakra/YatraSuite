package com.rslakra.microservice.yatrasuite.vehicleservice.entity;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.rslakra.microservice.yatrasuite.vehicleservice.persistence.entity.LocationHistory;
import com.rslakra.microservice.yatrasuite.vehicleservice.persistence.entity.Vehicle;
import com.rslakra.microservice.yatrasuite.vehicleservice.util.VehicleTestUtils;
import org.junit.jupiter.api.Test;

class LocationHistoryTest {

    @Test
    public void getInUse_shouldReturnFalse_ifNoRideTimesRecorded() {
        Vehicle vehicle = VehicleTestUtils.createVehicle();
        LocationHistory locationHistory = VehicleTestUtils.createLocationHistory(vehicle);
//        locationHistory.setLastRideStart(null);
//        locationHistory.setLastRideEnd(null);

        assertFalse(vehicle.isInUse());
    }

    @Test
    public void getInUse_shouldReturnFalse_ifNoStartTimeRecorded() {
        Vehicle vehicle = VehicleTestUtils.createVehicle();
        LocationHistory locationHistory = VehicleTestUtils.createLocationHistory(vehicle);
//        locationHistory.setLastRideStart(null);
//        locationHistory.setLastRideEnd(nextDateTime());

        assertFalse(vehicle.isInUse());
    }

    @Test
    public void getInUse_shouldReturnTrue_ifNoEndTimeRecorded() {
        Vehicle vehicle = VehicleTestUtils.createVehicle();
        LocationHistory locationHistory = VehicleTestUtils.createLocationHistory(vehicle);
//        locationHistory.setLastRideStart(nextDateTime());
//        locationHistory.setLastRideEnd(null);

        assertTrue(vehicle.isInUse());
    }

    @Test
    public void getInUse_shouldReturnTrue_ifStartTimeAfterEndTime() {
        Vehicle vehicle = VehicleTestUtils.createVehicle();
        LocationHistory locationHistory = VehicleTestUtils.createLocationHistory(vehicle);
//        locationHistory.setLastRideStart(nextDateTime().plusSeconds(1000));
//        locationHistory.setLastRideEnd(nextDateTime());

        assertTrue(vehicle.isInUse());
    }

    @Test
    public void getInUse_shouldReturnFalse_ifStartTimeBeforeEndTime() {
        Vehicle vehicle = VehicleTestUtils.createVehicle();
        LocationHistory locationHistory = VehicleTestUtils.createLocationHistory(vehicle);
//        locationHistory.setLastRideStart(nextDateTime());
//        locationHistory.setLastRideEnd(nextDateTime().plusSeconds(1000));

        assertFalse(vehicle.isInUse());
    }
}