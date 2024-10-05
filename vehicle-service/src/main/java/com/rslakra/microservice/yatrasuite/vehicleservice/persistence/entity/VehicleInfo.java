package com.rslakra.microservice.yatrasuite.vehicleservice.persistence.entity;

import com.rslakra.appsuite.core.ToString;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The <code>VehicleDetail</code> stores the vehicle extra details information.
 *
 * @author Rohtash Lakra
 * @created 7/25/23 8:50 PM
 */
@Getter
@Setter
@NoArgsConstructor
public class VehicleInfo {

    private String type;
    private String color;
    private String wear;
    private PurchaseInfo purchaseInfo;

    /**
     * Returns the string representation of this object.
     *
     * @return
     */
    @Override
    public String toString() {
        return ToString.of(Vehicle.class)
            .add("type", getType())
            .add("color", getColor())
            .add("wear", getWear())
            .add("purchaseInfo", getPurchaseInfo())
            .toString();
    }

}
