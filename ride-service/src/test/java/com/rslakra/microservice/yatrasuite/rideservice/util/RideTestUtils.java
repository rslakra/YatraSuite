package com.rslakra.microservice.yatrasuite.rideservice.util;

import static com.rslakra.appsuite.core.RandomUtils.nextRandomEmail;
import static com.rslakra.appsuite.core.RandomUtils.nextRandomId;
import static com.rslakra.appsuite.core.RandomUtils.nextRandomInt;
import static com.rslakra.appsuite.core.RandomUtils.nextRandomLatitude;
import static com.rslakra.appsuite.core.RandomUtils.nextRandomLongitude;
import static com.rslakra.appsuite.core.RandomUtils.nextRandomPercentage;
import static com.rslakra.appsuite.core.RandomUtils.nextRandomUuid;

import com.rslakra.microservice.yatrasuite.rideservice.dto.EndRideRequestDto;
import com.rslakra.microservice.yatrasuite.rideservice.dto.RideRequestDto;
import com.rslakra.microservice.yatrasuite.rideservice.persistence.entity.Ride;

import java.time.LocalDateTime;

public class RideTestUtils {

    public static Ride createRide() {
        Ride ride = new Ride();
        ride.setId(nextRandomId());
        ride.setUserEmail(nextRandomEmail());
        ride.setVehicleId(nextRandomUuid());
        ride.setStartTime(LocalDateTime.now());
        ride.setEndTime(LocalDateTime.now().plusSeconds(nextRandomInt(100000)));
        return ride;
    }

    public static RideRequestDto createRideRequest(Ride ride) {
        RideRequestDto dto = new RideRequestDto();
        dto.setEmail(ride.getUserEmail());
        dto.setVehicleId(ride.getVehicleId().toString());
        return dto;
    }

    public static RideRequestDto createRideRequest() {
        return createRideRequest(createRide());
    }

    public static EndRideRequestDto createEndRideRequest(Ride ride) {
        EndRideRequestDto dto = new EndRideRequestDto();
        dto.setEmail(ride.getUserEmail());
        dto.setVehicleId(ride.getVehicleId().toString());
        dto.setBattery(String.valueOf(nextRandomPercentage()));
        dto.setLatitude(String.valueOf(nextRandomLatitude()));
        dto.setLongitude(String.valueOf(nextRandomLongitude()));
        return dto;
    }

    public static EndRideRequestDto createEndRideRequest() {
        return createEndRideRequest(createRide());
    }
}
