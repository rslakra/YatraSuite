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
public class NewVehicleDTO {

    private UUID id;
    private String vin;
    @JsonProperty("vehicle_type")
    private String vehicleType;
    @JsonProperty("battery")
    private String batteryLevel;
    private String color;
    private String wear;
    @JsonProperty("serial_number")
    private String serialNumber;
    private String manufacturer;
    @JsonProperty("purchase_date")
    private String purchasedOn;
    private String longitude;
    private String latitude;

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
            .add("batteryLevel", getBatteryLevel())
            .add("color", getColor())
            .add("wear", getWear())
            .add("manufacturer", getManufacturer())
            .add("purchasedOn", getPurchasedOn())
            .add("latitude", getLatitude())
            .add("longitude", getLongitude())
            .toString();
    }

}
