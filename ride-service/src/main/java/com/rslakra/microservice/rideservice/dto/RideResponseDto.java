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
public class RideResponseDto {

    @JsonProperty("ride")
    private RideDto rideDTO;
    private String[] messages;

}
