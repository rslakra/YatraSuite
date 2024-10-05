package com.rslakra.microservice.yatrasuite.yatrathymeleaf.dto.vehicle;

import com.rslakra.appsuite.core.ToString;
import com.rslakra.appsuite.spring.payload.dto.AbstractEntityDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Rohtash Lakra
 * @created 7/25/23 8:50 PM
 */
@Getter
@Setter
@NoArgsConstructor
public class LocationHistory extends AbstractEntityDTO<UUID> {

    @JsonProperty("ts")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime lastRecordedAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime lastRideStart;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime lastRideEnd;
    private BigDecimal latitude;
    private BigDecimal longitude;

    /**
     * Returns the string representation of this object.
     *
     * @return
     */
    @Override
    public String toString() {
        return ToString.of(LocationHistory.class)
            .add("id", getId())
            .add("lastRecordedAt", getLastRecordedAt())
            .add("lastRideStart", getLastRideStart())
            .add("lastRideEnd", getLastRideEnd())
            .add("latitude", getLatitude())
            .add("longitude", getLongitude())
            .toString();
    }

}
