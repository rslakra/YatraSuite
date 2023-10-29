package com.rslakra.microservice.rideservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Rohtash Lakra
 * @created 7/23/23 9:16 PM
 */
@Getter
@Setter
@NoArgsConstructor
public class RideRequestDto {

    private String email;
    @JsonProperty("vehicle_id")
    private String vehicleId;

}
