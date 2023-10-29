package com.rslakra.microservice.vehicleservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rslakra.frameworks.core.ToString;
import com.rslakra.microservice.vehicleservice.persistence.entity.Vehicle;
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
public abstract class VehicleDTO {

    private UUID id;
    private String vin;
    @JsonProperty("serial_number")
    private int serialNumber;
    private int battery;
    @JsonProperty("vehicle_info")
    private VehicleDetailDTO vehicleInfo;
    private boolean inUse;

    /**
     * Returns the string representation of this object.
     *
     * @return
     */
    @Override
    public String toString() {
        return ToString.of(Vehicle.class)
            .add("id", getId())
            .add("vin", getVin())
            .add("serialNumber", getSerialNumber())
            .add("batteryLevel", getBattery())
            .add("vehicleInfo", getVehicleInfo())
            .add("inUse", isInUse())
            .toString();
    }

}
