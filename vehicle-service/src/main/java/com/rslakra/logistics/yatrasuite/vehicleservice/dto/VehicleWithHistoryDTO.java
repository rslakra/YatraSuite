package com.rslakra.logistics.yatrasuite.vehicleservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Data Transfer Object for Vehicle with Location History Information appended
 */

/**
 * @author Rohtash Lakra
 * @created 7/25/23 8:50 PM
 */
@Getter
@Setter
@NoArgsConstructor
public class VehicleWithHistoryDTO extends VehicleDTO {

    @JsonProperty(value = "locationHistory")
    List<LocationHistoryDTO> locationDetails;

    // Current/latest location for convenience (extracted from first location history entry)
    @JsonProperty(value = "last_latitude")
    private BigDecimal lastLatitude;

    @JsonProperty(value = "last_longitude")
    private BigDecimal lastLongitude;

    @JsonProperty(value = "timestamp")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime timestamp;

}
