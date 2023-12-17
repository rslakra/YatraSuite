package com.rslakra.microservice.yatrasuite.rideservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Holds ride info.
 *
 * @author Rohtash Lakra
 * @created 7/23/23 9:16 PM
 */
@Getter
@Setter
@NoArgsConstructor
public class RideDto {

    private Long id;
    @JsonProperty("vehicle_id")
    private UUID vehicleId;
    @JsonProperty("user_email")
    private String userEmail;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty("start_time")
    private LocalDateTime startTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty("end_time")
    private LocalDateTime endTime;

}
