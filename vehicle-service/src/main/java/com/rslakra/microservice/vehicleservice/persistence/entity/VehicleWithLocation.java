package com.rslakra.microservice.vehicleservice.persistence.entity;

import com.rslakra.frameworks.core.BeanUtils;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * The <code>VehicleWithLocation</code> contains a record which is a join of vehicles' table with last entry from
 * location_history table.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
// The @TypeDef annotations can be applied to a base entity class
@TypeDef(name = "json", typeClass = JsonType.class)
public class VehicleWithLocation {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "serial_number")
    private Integer serialNumber;

    @Column(name = "battery_level")
    private Integer batteryLevel;

    @Column(name = "last_ride_start")
    private LocalDateTime lastRideStart;

    @Column(name = "last_ride_end")
    private LocalDateTime lastRideEnd;

    @Type(type = "json")
    @Column(name = "vehicle_info", columnDefinition = "json")
    private VehicleInfo vehicleInfo;

    private LocalDateTime timestamp;
    private BigDecimal latitude;
    private BigDecimal longitude;

    /**
     * @return
     */
    @Transient
    public Boolean isInUse() {
        if (BeanUtils.isNull(getLastRideStart())) {
            return false;
        } else if (BeanUtils.isNull(getLastRideEnd())) {
            return true;
        } else {
            return getLastRideStart().isAfter(getLastRideEnd());
        }
    }
}
