package com.rslakra.microservice.vehicleservice.persistence.entity;

import com.devamatre.framework.core.ToString;
import com.devamatre.framework.spring.persistence.entity.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
//    @Transient
//    public Boolean isInUse() {
//        if (BeanUtils.isNull(getLastRideStart())) {
//            return false;
//        } else if (BeanUtils.isNull(getLastRideEnd())) {
//            return true;
//        } else {
//            return getLastRideEnd().isAfter(getLastRideStart());
//        }
//    }


    /**
     * Returns the string representation of this object.
     *
     * @return
     */
    @Override
    public String toString() {
        return ToString.of(Vehicle.class)
            .add("id", getId())
//            .add("lastRideStart", getLastRideStart())
//            .add("lastRideEnd", getLastRideEnd())
            .add("latitude", getLatitude())
            .add("longitude", getLongitude())
            .toString();
    }

}
