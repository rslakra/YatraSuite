package com.rslakra.microservice.yatrasuite.vehicleservice.kafka.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * @author Rohtash Lakra
 * @created 7/31/23 1:39 PM
 */
@Getter
@NoArgsConstructor
@SuperBuilder
public class RideStartedEvent extends AbstractRideEvent implements Event {

    private LocalDateTime startTime;

    /**
     * @return
     */
    @Override
    public EventType getType() {
        return EventType.RideStarted;
    }
}

