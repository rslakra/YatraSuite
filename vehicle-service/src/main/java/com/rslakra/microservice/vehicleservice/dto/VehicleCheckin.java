package com.rslakra.microservice.vehicleservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.rslakra.frameworks.core.ToString;
import com.rslakra.microservice.vehicleservice.persistence.entity.Vehicle;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Rohtash Lakra
 * @created 7/29/23 6:23 PM
 */
@Getter
@Setter
@NoArgsConstructor
public class VehicleCheckin {

    private UUID vehicleId;
    private Integer batteryLevel;
    @JsonProperty(value = "lastRecordedAt")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime lastRecordedAt;
    private BigDecimal latitude;
    private BigDecimal longitude;

    /**
     * Returns the string representation of this object.
     *
     * @return
     */
    @Override
    public String toString() {
        return ToString.of(Vehicle.class)
            .add("vehicleId", getVehicleId())
            .add("batteryLevel", getBatteryLevel())
            .add("lastRecordedAt", getLastRecordedAt())
            .add("latitude", getLatitude())
            .add("longitude", getLongitude())
            .toString();
    }

}
