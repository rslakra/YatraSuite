package com.rslakra.microservice.rideservice.persistence.entity;

import com.devamatre.framework.core.ToString;
import com.devamatre.framework.spring.persistence.entity.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Represents <code>rides</code> table.
 *
 * @author Rohtash Lakra
 * @created 7/23/23 9:16 PM
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "rides")
public class Ride extends AbstractEntity<Long> {

    @Column(name = "user_email")
    private String userEmail;
    @Column(name = "vehicle_id")
    private UUID vehicleId;
    @Column(name = "start_time")
    private LocalDateTime startTime;
    @Column(name = "end_time")
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
            .add("userEmail", getUserEmail())
            .add("vehicleId", getVehicleId())
            .add("startTime", getStartTime())
            .add("endTime", getEndTime())
            .toString();
    }

}
