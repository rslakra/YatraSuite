package com.rslakra.logistics.yatrasuite.vehicleservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rslakra.logistics.yatrasuite.framework.TimeUtils;
import com.rslakra.logistics.yatrasuite.vehicleservice.persistence.entity.VehicleInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.util.Map;

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
public class VehicleDetailDTO {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    private String vin;
    private String type;
    private String color;
    private String wear;
    @JsonProperty("purchase_information")
    private PurchaseInfo purchaseInfo;

    /**
     * @param vehicleInfo
     */
    public VehicleDetailDTO(VehicleDetailDTO vehicleInfo) {
        this.vin = vehicleInfo.getVin();
        this.type = vehicleInfo.getType();
        this.color = vehicleInfo.getColor();
        this.wear = vehicleInfo.getWear();
        this.purchaseInfo = vehicleInfo.getPurchaseInfo();
    }

    /**
     * @param newVehicleDTO
     */
    public VehicleDetailDTO(NewVehicleDTO newVehicleDTO) {
        this.vin = newVehicleDTO.getVin();
        this.type = newVehicleDTO.getVehicleType();
        this.color = newVehicleDTO.getColor();
        this.wear = newVehicleDTO.getWear();
        this.purchaseInfo = new PurchaseInfo();
        this.purchaseInfo.setSerialNumber(Integer.parseInt(newVehicleDTO.getSerialNumber()));
        this.purchaseInfo.setManufacturer(newVehicleDTO.getManufacturer());
        this.purchaseInfo.setPurchasedOn(TimeUtils.parseDate(newVehicleDTO.getPurchasedOn()));
    }

    /**
     * @param vehicleInfo
     */
    public VehicleDetailDTO(Map<String, Object> vehicleInfo) throws JsonProcessingException {
        this(OBJECT_MAPPER.readValue(toJsonString(vehicleInfo), VehicleDetailDTO.class));
    }

    /**
     * @param vehicleInfo
     * @return
     */
    private static String toJsonString(Map<String, Object> vehicleInfo) {
        try {
            return OBJECT_MAPPER.writeValueAsString(vehicleInfo);
        } catch (JsonProcessingException ex) {
            return null;
        }
    }

    /**
     * @return
     */
    public String toJsonString() {
        try {
            return OBJECT_MAPPER.writeValueAsString(this);
        } catch (JsonProcessingException ex) {
            return null;
        }
    }

    /**
     * Converts this DTO to a VehicleInfo entity.
     *
     * @return VehicleInfo entity with purchaseInfo properly set
     */
    public VehicleInfo toVehicleInfo() {
        VehicleInfo vehicleInfo = new VehicleInfo();
        vehicleInfo.setType(this.type);
        vehicleInfo.setColor(this.color);
        vehicleInfo.setWear(this.wear);

        // Set purchase info on the entity (not on 'this')
        if (this.purchaseInfo != null) {
            com.rslakra.logistics.yatrasuite.vehicleservice.persistence.entity.PurchaseInfo entityPurchaseInfo =
                new com.rslakra.logistics.yatrasuite.vehicleservice.persistence.entity.PurchaseInfo();
            entityPurchaseInfo.setSerialNumber(this.purchaseInfo.getSerialNumber());
            entityPurchaseInfo.setManufacturer(this.purchaseInfo.getManufacturer());
            entityPurchaseInfo.setPurchasedOn(this.purchaseInfo.getPurchasedOn());
            vehicleInfo.setPurchaseInfo(entityPurchaseInfo);
        }

        return vehicleInfo;
    }


}
