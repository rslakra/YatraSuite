package com.rslakra.microservice.yatrasuite.vehicleservice.util;

import static com.devamatre.appsuite.core.RandomUtils.nextDateTime;
import static com.devamatre.appsuite.core.RandomUtils.nextLatitudeBigDecimal;
import static com.devamatre.appsuite.core.RandomUtils.nextLongitudeBigDecimal;
import static com.devamatre.appsuite.core.RandomUtils.nextRandomId;
import static com.devamatre.appsuite.core.RandomUtils.nextRandomInt;
import static com.devamatre.appsuite.core.RandomUtils.nextRandomPercentage;
import static com.devamatre.appsuite.core.RandomUtils.nextRandomString;
import static com.devamatre.appsuite.core.RandomUtils.nextRandomUuid;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rslakra.microservice.yatrasuite.vehicleservice.dto.NewVehicleDTO;
import com.rslakra.microservice.yatrasuite.vehicleservice.dto.VehicleDetailDTO;
import com.rslakra.microservice.yatrasuite.vehicleservice.persistence.entity.LocationHistory;
import com.rslakra.microservice.yatrasuite.vehicleservice.persistence.entity.Vehicle;
import com.rslakra.microservice.yatrasuite.vehicleservice.persistence.entity.VehicleWithLocation;
import org.mockito.ArgumentMatcher;
import org.mockito.internal.matchers.InstanceOf;
import org.mockito.internal.progress.ThreadSafeMockingProgress;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public enum VehicleTestUtils {
    INSTANCE;


    /**
     * @param matcher
     */
    private static void reportMatcher(ArgumentMatcher<?> matcher) {
        ThreadSafeMockingProgress.mockingProgress().getArgumentMatcherStorage().reportMatcher(matcher);
    }

    /**
     * @return
     */
    public static BigDecimal anyBigDecimal() {
        reportMatcher(new InstanceOf(BigDecimal.class, "<any BigDecimal>"));
        return BigDecimal.valueOf(0.0);
    }

    public static Vehicle createVehicle() {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(nextRandomUuid());
        vehicle.setSerialNumber(nextRandomInt(10000));
        vehicle.setBatteryLevel(nextRandomPercentage());
//        vehicle.setLastRideStart(nextDateTime());
//        vehicle.setLastRideEnd(nextDateTime().plusSeconds(1000));
        vehicle.setVehicleInfo(createVehicleInfoDTO().toVehicleInfo());

        List<LocationHistory> locations = IntStream.range(0, nextRandomInt(5) + 1)
            .mapToObj(i -> createLocationHistory(vehicle))
            .collect(Collectors.toList());

        vehicle.setLocationHistories(locations);

        return vehicle;
    }

    public static VehicleWithLocation createVehicleWithLocation() {
        VehicleWithLocation vehicleWithLocation = new VehicleWithLocation();
//        vehicleWithLocation.setId(nextRandomId());
        vehicleWithLocation.setSerialNumber(nextRandomInt(10000));
        vehicleWithLocation.setBatteryLevel(nextRandomPercentage());
        vehicleWithLocation.setLastRideStart(nextDateTime());
        vehicleWithLocation.setLastRideEnd(nextDateTime().plusSeconds(1000));
//        vehicleWithLocation.setVehicleInfo(createVehicleInfoDTO().toVehicleInfo());
        return vehicleWithLocation;
    }

    public static LocationHistory createLocationHistory(Vehicle vehicle) {
        LocationHistory locationHistory = new LocationHistory();
        locationHistory.setId(nextRandomId());
        locationHistory.setVehicle(vehicle);
//        locationHistory.setLastRecordedAt(nextDateTime().plusSeconds(1000));
//        locationHistory.setLastRideStart(nextDateTime());
//        locationHistory.setLastRideEnd(nextDateTime().plusSeconds(1000));
        locationHistory.setLatitude(nextLatitudeBigDecimal());
        locationHistory.setLongitude(nextLongitudeBigDecimal());
        return locationHistory;
    }

    public static VehicleDetailDTO createVehicleInfoDTO() {
        VehicleDetailDTO vehicleDetailDTO = null;
        Map<String, Object> vehicleInfo = new HashMap<>();
        vehicleInfo.put("type", nextRandomString("Type"));
        try {
            vehicleDetailDTO = new VehicleDetailDTO(vehicleInfo);
        } catch (JsonProcessingException ex) {
//            throw new RuntimeException(e);
        }

        return vehicleDetailDTO;
    }

    public static NewVehicleDTO createNewVehicleDTO() {
        NewVehicleDTO newVehicleDTO = new NewVehicleDTO();
        newVehicleDTO.setBatteryLevel(String.valueOf(nextRandomPercentage()));
        newVehicleDTO.setColor(nextRandomString("Color"));
        newVehicleDTO.setVehicleType(nextRandomString("Type"));
        newVehicleDTO.setId(nextRandomUuid());
        newVehicleDTO.setManufacturer(nextRandomString("Manufacturer"));
        newVehicleDTO.setPurchasedOn(nextDateTime().toString());
        newVehicleDTO.setSerialNumber(String.valueOf(nextRandomInt(1000000)));
        newVehicleDTO.setLatitude(nextLatitudeBigDecimal().toString());
        newVehicleDTO.setLongitude(nextLongitudeBigDecimal().toString());
        newVehicleDTO.setWear(nextRandomString("Wear"));
        return newVehicleDTO;
    }

}
