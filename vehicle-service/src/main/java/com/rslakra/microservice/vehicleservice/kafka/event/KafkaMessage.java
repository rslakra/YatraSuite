package com.rslakra.microservice.vehicleservice.kafka.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Rohtash Lakra
 * @created 7/31/23 3:56 PM
 */
@Getter
@Setter
@NoArgsConstructor
public class KafkaMessage {

    @JsonProperty("after")
    private EventEnvelope message;

}
