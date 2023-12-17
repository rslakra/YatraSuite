package com.rslakra.microservice.yatrasuite.vehicleservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Data Transfer Object for Vehicle with Location History Information appended
 */

/**
 * @author Rohtash Lakra
 * @created 7/25/23 8:50 PM
 */
@Getter
@Setter
@NoArgsConstructor
public class VehicleWithHistoryDTO extends VehicleDTO {

    @JsonProperty(value = "locationHistory")
    List<LocationHistoryDTO> locationDetails;

}
