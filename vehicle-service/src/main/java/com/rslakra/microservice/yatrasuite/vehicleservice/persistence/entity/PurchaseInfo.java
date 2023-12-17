package com.rslakra.microservice.yatrasuite.vehicleservice.persistence.entity;

import com.devamatre.appsuite.core.ToString;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * <pre>
 *  {
 *      "serialNumber": 160175,
 *      "manufacturer": "Lakra Inc",
 *      "purchasedOn": "2023-07-31 01:45:18"
 *  }
 * </pre>
 *
 * @author Rohtash Lakra
 * @created 7/25/23 8:50 PM
 */
@Getter
@Setter
@NoArgsConstructor
public class PurchaseInfo {

    private Integer serialNumber;
    private String manufacturer;
    private Date purchasedOn;

    /**
     * Returns the string representation of this object.
     *
     * @return
     */
    @Override
    public String toString() {
        return ToString.of(Vehicle.class)
            .add("serialNumber", getSerialNumber())
            .add("manufacturer", getManufacturer())
            .add("purchasedOn", getPurchasedOn())
            .toString();
    }

}
