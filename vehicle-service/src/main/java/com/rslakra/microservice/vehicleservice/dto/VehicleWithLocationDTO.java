package com.rslakra.microservice.vehicleservice.dto;

import com.devamatre.framework.core.ToString;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Data Transfer Object for Vehicle with Location Information appended
 */

/**
 * @author Rohtash Lakra
 * @created 7/25/23 8:50 PM
 */
@Getter
@Setter
@NoArgsConstructor
public class VehicleWithLocationDTO extends VehicleDTO {

//    @JsonProperty(value = "lastRecordedAt")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
//    private LocalDateTime lastRecordedAt;
//
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
//    private LocalDateTime lastRideStart;
//
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
//    private LocalDateTime lastRideEnd;

//    @JsonProperty(value = "latitude")
//    private BigDecimal latitude;
//
//    @JsonProperty(value = "longitude")
//    private BigDecimal longitude;

    @JsonProperty(value = "last_latitude")
    private BigDecimal lastLatitude;
    @JsonProperty(value = "last_longitude")
    private BigDecimal lastLongitude;
    @JsonProperty(value = "timestamp")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime timestamp;


    /**
     * Returns the string representation of this object.
     *
     * @return
     */
    @Override
    public String toString() {
        return ToString.of(VehicleWithLocationDTO.class)
            .add("id", getId())
            .add("vin", getVin())
            .add("serialNumber", getSerialNumber())
            .add("battery", getBattery())
            .add("vehicleInfo", getVehicleInfo())
            .add("inUse", isInUse())
//            .add("lastRecordedAt", getLastRecordedAt())
//            .add("lastRideStart", getLastRideStart())
//            .add("lastRideEnd", getLastRideEnd())
//            .add("latitude", getLatitude())
//            .add("longitude", getLongitude())

            .add("timestamp", getTimestamp())
            .add("latitude", getLastLatitude())
            .add("longitude", getLastLongitude())

            .toString();
    }

}
