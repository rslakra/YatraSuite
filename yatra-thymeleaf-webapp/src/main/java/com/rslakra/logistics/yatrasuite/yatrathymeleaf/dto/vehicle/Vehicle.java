package com.rslakra.logistics.yatrasuite.yatrathymeleaf.dto.vehicle;

import com.rslakra.appsuite.core.ToString;
import com.rslakra.appsuite.spring.payload.dto.AbstractEntityDTO;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonAlias("serial_number")  // Accept from backend response
    private int serialNumber;
    @JsonAlias("battery")  // Accept from backend response
    private int batteryLevel;
    @JsonAlias("vehicle_info")  // Accept from backend response
    private VehicleDetail vehicleDetail = new VehicleDetail();  // Initialize for form binding
    private boolean inUse;
    @JsonAlias("last_latitude")  // Accept from backend response
    private BigDecimal lastLatitude;
    @JsonAlias("last_longitude")  // Accept from backend response
    private BigDecimal lastLongitude;
    @JsonAlias("timestamp")  // Accept from backend response
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime lastRecordedAt;

    @JsonAlias("locationHistory")  // Accept from backend response
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
            .add("batteryLevel", getBatteryLevel())
            .add("vehicleDetail", getVehicleDetail())
            .add("inUse", isInUse())
            .add("lastLatitude", getLastLatitude())
            .add("lastLongitude", getLastLongitude())
            .add("locationHistory", getLocationHistories())
            .toString();
    }

}
