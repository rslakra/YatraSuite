package com.rslakra.microservice.yatrasuite.rideservice.kafka.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

/**
 * @author Rohtash Lakra
 * @created 7/31/23 1:41 PM
 */
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public abstract class AbstractRideEvent implements Event {

    private Long rideId;
    private String userEmail;
    private UUID vehicleId;

}
