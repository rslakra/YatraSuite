package com.rslakra.microservice.yatrasuite.yatrathymeleaf.dto.ride;

import com.devamatre.appsuite.core.ToString;
import com.devamatre.appsuite.spring.payload.dto.AbstractEntityDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Holds ride info.
 *
 * @author Rohtash Lakra
 * @created 7/23/23 9:16 PM
 */
@Getter
@Setter
@NoArgsConstructor
public class Ride extends AbstractEntityDTO<Long> {

    //    @JsonProperty("vehicle_id")
    private UUID vehicleId;
    //    @JsonProperty("user_email")
    private String userEmail;
    //    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
//    @JsonProperty("start_time")
    private LocalDateTime startTime;
    //    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
//    @JsonProperty("end_time")
    private LocalDateTime endTime;

    /**
     * Returns the string representation of this object.
     *
     * @return
     */
    @Override
    public String toString() {
        return ToString.of(Ride.class)
            .add("id", getId())
            .add("email", getUserEmail())
            .add("vehicleId", getVehicleId())
            .add("startTime", getStartTime())
            .add("endTime", getEndTime())
            .toString();
    }

}
