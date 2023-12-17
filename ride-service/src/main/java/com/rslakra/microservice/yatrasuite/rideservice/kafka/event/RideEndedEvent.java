package com.rslakra.microservice.yatrasuite.rideservice.kafka.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Rohtash Lakra
 * @created 7/31/23 1:39 PM
 */
@Getter
@NoArgsConstructor
@SuperBuilder
public class RideEndedEvent extends AbstractRideEvent implements Event {

    private Integer battery;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private LocalDateTime endTime;

    /**
     * @return
     */
    @Override
    public EventType getType() {
        return EventType.RideEnded;
    }
}
