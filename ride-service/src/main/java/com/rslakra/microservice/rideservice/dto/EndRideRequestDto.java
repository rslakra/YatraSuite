package com.rslakra.microservice.rideservice.dto;

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
public class EndRideRequestDto extends RideRequestDto {

    private String battery;
    private String latitude;
    private String longitude;

}
