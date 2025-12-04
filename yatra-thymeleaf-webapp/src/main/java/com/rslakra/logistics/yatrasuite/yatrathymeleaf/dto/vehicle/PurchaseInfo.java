package com.rslakra.logistics.yatrasuite.yatrathymeleaf.dto.vehicle;

import com.rslakra.appsuite.core.ToString;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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

    @JsonAlias("serial_number")  // Accept from backend response
    private Integer serialNumber;
    private String manufacturer;
    @JsonAlias("purchase_date")  // Accept from backend response
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd")  // For form binding (HTML date input sends yyyy-MM-dd)
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
