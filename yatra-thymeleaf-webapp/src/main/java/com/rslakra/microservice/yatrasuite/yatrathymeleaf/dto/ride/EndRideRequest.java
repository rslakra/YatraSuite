package com.rslakra.microservice.yatrasuite.yatrathymeleaf.dto.ride;

import com.devamatre.appsuite.core.ToString;
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
public class EndRideRequest extends RideRequest {

    private String battery;
    private String longitude;
    private String latitude;

    /**
     * Returns the string representation of this object.
     *
     * @return
     */
    @Override
    public String toString() {
        return ToString.of(EndRideRequest.class)
            .add("email", getEmail())
            .add("vehicleId", getVehicleId())
            .add("batteryLevel", getBattery())
            .add("latitude", getLatitude())
            .add("longitude", getLongitude())
            .toString();
    }

}
