package com.rslakra.microservice.yatrathymeleaf.dto.vehicle;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Rohtash Lakra
 * @created 8/8/23 3:28 PM
 */
public enum VehicleType {

    SPORTS("Sports"),
    HATCHBACK("Hatchback"),
    COUPE("Coupe"),
    SEDAN("Sedan"),
    BIKE("Bike"),
    SCOOTER("Scooter"),
    SKATEBOARD("Skateboard"),
    ;

    private final String type;

    VehicleType(final String type) {
        this.type = type;
    }

    /**
     * @param vehicleType
     * @return
     */
    public static VehicleType ofString(final String vehicleType) {
        return Arrays.stream(values())
            .filter(entry -> entry.name().equalsIgnoreCase(vehicleType))
            .findFirst()
            .orElse(null);
    }

    /**
     * @return
     */
    public static List<String> asList() {
        return Arrays.stream(values())
            .map(vehicleType -> vehicleType.name())
            .collect(Collectors.toList());
    }
}
