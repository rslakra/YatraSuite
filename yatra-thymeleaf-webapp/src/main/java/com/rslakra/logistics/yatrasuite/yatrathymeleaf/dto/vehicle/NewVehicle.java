package com.rslakra.logistics.yatrasuite.yatrathymeleaf.dto.vehicle;

import com.rslakra.appsuite.core.ToString;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/**
 * @author Rohtash Lakra
 * @created 7/25/23 8:50 PM
 */
@Getter
@Setter
@NoArgsConstructor
public class NewVehicle {

    private UUID id;
    private String vin;
    @JsonProperty("vehicle_type")
    private String vehicleType;
    @JsonProperty("battery")
    private String batteryLevel;
    private String color;
    private String wear;
    @JsonProperty("serial_number")
    private String serialNumber;
    private String manufacturer;
    @JsonProperty("purchase_date")
    private String purchasedOn;
    private String latitude;
    private String longitude;

    /**
     * Creates a NewVehicle from a Vehicle object for API submission.
     *
     * @param vehicle the Vehicle object from the form
     * @return a NewVehicle DTO matching the backend API structure
     */
    public static NewVehicle fromVehicle(Vehicle vehicle) {
        NewVehicle newVehicle = new NewVehicle();
        newVehicle.setId(vehicle.getId());
        newVehicle.setVin(vehicle.getVin());
        newVehicle.setBatteryLevel(String.valueOf(vehicle.getBatteryLevel()));
        newVehicle.setLatitude(vehicle.getLastLatitude() != null ? vehicle.getLastLatitude().toPlainString() : null);
        newVehicle.setLongitude(vehicle.getLastLongitude() != null ? vehicle.getLastLongitude().toPlainString() : null);

        if (vehicle.getVehicleDetail() != null) {
            VehicleDetail detail = vehicle.getVehicleDetail();
            newVehicle.setVehicleType(detail.getType());
            newVehicle.setColor(detail.getColor());
            newVehicle.setWear(detail.getWear());

            if (detail.getPurchaseInfo() != null) {
                PurchaseInfo purchaseInfo = detail.getPurchaseInfo();
                newVehicle.setSerialNumber(purchaseInfo.getSerialNumber() != null 
                    ? String.valueOf(purchaseInfo.getSerialNumber()) 
                    : String.valueOf(vehicle.getSerialNumber()));
                newVehicle.setManufacturer(purchaseInfo.getManufacturer());
                if (purchaseInfo.getPurchasedOn() != null) {
                    newVehicle.setPurchasedOn(new java.text.SimpleDateFormat("yyyy-MM-dd").format(purchaseInfo.getPurchasedOn()));
                }
            }
        }
        return newVehicle;
    }

    /**
     * Returns the string representation of this object.
     *
     * @return
     */
    @Override
    public String toString() {
        return ToString.of(NewVehicle.class)
            .add("id", getId())
            .add("vin", getVin())
            .add("vehicleType", getVehicleType())
            .add("serialNumber", getSerialNumber())
            .add("batteryLevel", getBatteryLevel())
            .add("color", getColor())
            .add("wear", getWear())
            .add("manufacturer", getManufacturer())
            .add("purchasedOn", getPurchasedOn())
            .add("latitude", getLatitude())
            .add("longitude", getLongitude())
            .toString();
    }

}
