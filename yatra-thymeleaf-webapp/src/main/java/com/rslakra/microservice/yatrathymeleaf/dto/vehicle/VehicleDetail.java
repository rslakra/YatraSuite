package com.rslakra.microservice.yatrathymeleaf.dto.vehicle;

import com.devamatre.framework.core.ToString;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.rslakra.microservice.framework.TimeUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <pre>
 *  {
 *     "vin": "0abe77b9-6a4e-433b-aa09-4185b242d007",
 *     "type": "scooter",
 *     "color": "white",
 *     "wear": "mint",
 *     "purchaseInfo": {
 *         "serialNumber": 160175,
 *         "manufacturer": "Lakra Inc",
 *         "purchasedOn": "2023-07-31 01:45:18"
 *     }
 *  }
 * </pre>
 *
 * @author Rohtash Lakra
 * @created 7/25/23 8:50 PM
 */
@Getter
@Setter
@NoArgsConstructor
public class VehicleDetail {

    private String vin;
    private String type;
    private String color;
    private String wear;
    @JsonProperty("purchase_information")
    private PurchaseInfo purchaseInfo;

    /**
     * @param vehicleInfo
     */
    public VehicleDetail(VehicleDetail vehicleInfo) {
        this.vin = vehicleInfo.getVin();
        this.type = vehicleInfo.getType();
        this.color = vehicleInfo.getColor();
        this.wear = vehicleInfo.getWear();
        this.purchaseInfo = vehicleInfo.getPurchaseInfo();
    }

    /**
     * @param newVehicle
     */
    public VehicleDetail(NewVehicle newVehicle) {
        this.vin = newVehicle.getVin();
        this.type = newVehicle.getType();
        this.color = newVehicle.getColor();
        this.wear = newVehicle.getWear();
        this.purchaseInfo = new PurchaseInfo();
        this.purchaseInfo.setSerialNumber(Integer.parseInt(newVehicle.getSerialNumber()));
        this.purchaseInfo.setManufacturer(newVehicle.getManufacturer());
        this.purchaseInfo.setPurchasedOn(TimeUtils.parseDate(newVehicle.getPurchasedOn()));
    }

    /**
     * Returns the string representation of this object.
     *
     * @return
     */
    @Override
    public String toString() {
        return ToString.of(VehicleDetail.class)
            .add("type", getType())
            .add("color", getColor())
            .add("wear", getWear())
            .add("purchaseInfo", getPurchaseInfo())
            .toString();
    }

}
