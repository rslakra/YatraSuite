package com.rslakra.microservice.yatrasuite.yatrathymeleaf.dto.ride;

import com.rslakra.appsuite.core.ToString;
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
public class RideRequest {

    private String email;
    @JsonProperty("vehicle_id")
    private String vehicleId;

    /**
     * Returns the string representation of this object.
     *
     * @return
     */
    @Override
    public String toString() {
        return ToString.of(RideRequest.class)
            .add("email", getEmail())
            .add("vehicleId", getVehicleId())
            .toString();
    }

}
