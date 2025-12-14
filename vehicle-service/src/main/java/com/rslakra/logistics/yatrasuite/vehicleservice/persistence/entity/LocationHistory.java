package com.rslakra.logistics.yatrasuite.vehicleservice.persistence.entity;

import com.rslakra.appsuite.core.ToString;
import com.rslakra.appsuite.spring.persistence.entity.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.math.BigDecimal;

/**
 * The <code>LocationHistory</code> entity stores the vehicle's history details.
 *
 * @author Rohtash Lakra
 * @created 7/25/23 8:50 PM
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "location_history")
public class LocationHistory extends AbstractEntity<Long> {

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id", nullable = false)
    private Vehicle vehicle;

    @Column(name = "latitude")
    private BigDecimal latitude;

    @Column(name = "longitude")
    private BigDecimal longitude;

    /**
     * Returns if the vehicle is in use or not.
     *
     * @return
     */
    @Transient
    public Boolean isInUse() {
        return vehicle.isInUse();
    }


    /**
     * Returns the string representation of this object.
     *
     * @return
     */
    @Override
    public String toString() {
        return ToString.of(LocationHistory.class)
                .add("id", getId())
                //            .add("lastRideStart", getLastRideStart())
                //            .add("lastRideEnd", getLastRideEnd())
                .add("latitude", getLatitude())
                .add("longitude", getLongitude())
                .toString();
    }

}
