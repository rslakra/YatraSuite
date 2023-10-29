package com.rslakra.microservice.vehicleservice.kafka.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rslakra.frameworks.core.Payload;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Rohtash Lakra
 * @created 7/31/23 3:53 PM
 */
@Getter
@Setter
@NoArgsConstructor
public class EventEnvelope {

    @JsonProperty("event_type")
    private String eventType;

    @JsonProperty("event_data")
    private Payload<String, Object> eventData;

}
