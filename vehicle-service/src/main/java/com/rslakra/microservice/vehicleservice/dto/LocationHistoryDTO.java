package com.rslakra.microservice.vehicleservice.dto;

import com.devamatre.framework.core.ToString;
import com.devamatre.framework.spring.payload.dto.AbstractEntityDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Rohtash Lakra
 * @created 7/25/23 8:50 PM
 */
@Getter
@Setter
@NoArgsConstructor
public class LocationHistoryDTO extends AbstractEntityDTO {

    @JsonProperty("ts")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime lastRecordedAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime lastRideStart;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime lastRideEnd;

    @JsonProperty(value = "latitude")
    private BigDecimal latitude;

    @JsonProperty(value = "longitude")
    private BigDecimal longitude;

    /**
     * Returns the string representation of this object.
     *
     * @return
     */
    @Override
    public String toString() {
        return ToString.of(LocationHistoryDTO.class)
            .add("id", getId())
            .add("lastRecordedAt", getLastRecordedAt())
            .add("lastRideStart", getLastRideStart())
            .add("lastRideEnd", getLastRideEnd())
            .add("latitude", getLatitude())
            .add("longitude", getLongitude())
            .toString();
    }

}
