package com.rslakra.microservice.yatrasuite.yatrathymeleaf.dto.vehicle;

import com.rslakra.appsuite.core.ToString;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class NewVehicle {

    private UUID id;
    private String vin;
    private String type;
    @JsonProperty("battery")
    private String batteryLevel;
    private String color;
    private String wear;
    @JsonProperty("serial_number")
    private String serialNumber;
    private String manufacturer;
    @JsonProperty("purchase_date")
    private String purchasedOn;
    private String latitude;
    private String longitude;

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
