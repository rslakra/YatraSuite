package com.rslakra.microservice.rideservice.util;

import static com.devamatre.framework.core.RandomUtils.nextRandomEmail;
import static com.devamatre.framework.core.RandomUtils.nextRandomId;
import static com.devamatre.framework.core.RandomUtils.nextRandomInt;
import static com.devamatre.framework.core.RandomUtils.nextRandomLatitude;
import static com.devamatre.framework.core.RandomUtils.nextRandomLongitude;
import static com.devamatre.framework.core.RandomUtils.nextRandomPercentage;
import static com.devamatre.framework.core.RandomUtils.nextRandomUuid;

import com.rslakra.microservice.rideservice.dto.EndRideRequestDto;
import com.rslakra.microservice.rideservice.dto.RideRequestDto;
import com.rslakra.microservice.rideservice.persistence.entity.Ride;

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
