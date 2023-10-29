package com.rslakra.microservice.yatrathymeleaf.dto.vehicle;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.rslakra.frameworks.core.ToString;
import com.rslakra.frameworks.spring.payload.dto.AbstractEntityDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @author Rohtash Lakra
 * @created 7/25/23 8:50 PM
 */
@Getter
@Setter
@NoArgsConstructor
public class Vehicle extends AbstractEntityDTO<UUID> {

    private String vin;
    @JsonProperty("serial_number")
    private int serialNumber;
    private int battery;
    @JsonProperty("vehicle_info")
    private VehicleDetail vehicleDetail;
    private boolean inUse;
    @JsonProperty("last_latitude")
    private BigDecimal latitude;
    @JsonProperty("last_longitude")
    private BigDecimal longitude;
    @JsonProperty("timestamp")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime lastRecordedAt;

    @JsonProperty("locationHistory")
    private List<LocationHistory> locationHistories;


    /**
     * This field is used to display drop-down box for the vehicleType property.
     */
    private List<String> vehicleTypes = VehicleType.asList();

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
            .add("vehicleDetail", getVehicleDetail())
            .add("inUse", isInUse())
            .add("locationHistory", getLocationHistories())
            .toString();
    }

}
