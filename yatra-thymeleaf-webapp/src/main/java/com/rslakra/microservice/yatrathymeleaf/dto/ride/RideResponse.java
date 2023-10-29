package com.rslakra.microservice.yatrathymeleaf.dto.ride;

import com.devamatre.framework.core.ToString;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author Rohtash Lakra
 * @created 7/23/23 9:16 PM
 */
@Getter
@Setter
@NoArgsConstructor
public class RideResponse {

    @JsonProperty("ride")
    private Ride ride;
    private List<String> messages;

    /**
     * Returns the string representation of this object.
     *
     * @return
     */
    @Override
    public String toString() {
        return ToString.of(RideResponse.class)
            .add("ride", getRide())
            .add("messages", getMessages())
            .toString();
    }

}
