package com.rslakra.microservice.vehicleservice.controller;

import com.devamatre.framework.core.BeanUtils;
import com.rslakra.microservice.framework.TimeUtils;
import com.rslakra.microservice.framework.advice.AbstractResponse;
import com.rslakra.microservice.vehicleservice.dto.LocationHistoryDTO;
import com.rslakra.microservice.vehicleservice.dto.VehicleDetailDTO;
import com.rslakra.microservice.vehicleservice.dto.VehicleWithHistoryDTO;
import com.rslakra.microservice.vehicleservice.dto.VehicleWithLocationDTO;
import com.rslakra.microservice.vehicleservice.persistence.entity.LocationHistory;
import com.rslakra.microservice.vehicleservice.persistence.entity.Vehicle;
import com.rslakra.microservice.vehicleservice.persistence.entity.VehicleInfo;
import com.rslakra.microservice.vehicleservice.persistence.entity.VehicleWithLocation;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The <code>VehicleUtils</code> manages the vehicle objects.
 *
 * @author Rohtash Lakra
 * @created 7/25/23 8:50 PM
 */
public enum VehicleUtils {

    INSTANCE;

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    /**
     * Converts a list of Vehicle entity objects to a list of VehicleWithLocationDTO.
     *
     * @param vehicles list of Vehicle objects
     * @return List of RideWithVehicleDTOs
     */
    public static List<VehicleWithLocationDTO> toVehicleWithLocationDTOs(List<Vehicle> vehicles) {
        return vehicles.stream()
            .map(vehicle -> toVehicleWithLocationDto(vehicle))
            .collect(Collectors.toList());
    }

    /**
     * Converts a list of VehicleWithLocation entity objects to a list of VehicleWithLocationDTO objects.
     *
     * @param vehicleWithLocations List of VehicleWithLocation objects
     * @return List of VehicleWithLocationDTO's
     */
    public static List<VehicleWithLocationDTO> toVehicleWithLocationDTOList(
        List<VehicleWithLocation> vehicleWithLocations) {
        return vehicleWithLocations.stream()
            .map(vehicleWithLocation -> toVehicleWithLocationDTO(vehicleWithLocation))
            .collect(Collectors.toList());
    }

    /**
     * @param vehicleDetail
     * @return
     */
//    public static VehicleDetailDTO toVehicleDetailDto(VehicleDetail vehicleDetail) {
//        VehicleDetailDTO vehicleDetailDTO = MODEL_MAPPER.map(vehicleDetail, VehicleDetailDTO.class);
//        vehicleDetailDTO.setPurchaseInfo(MODEL_MAPPER.map(vehicleDetail, PurchaseInfo.class));
////        vehicleDetailDTO.getPurchaseInfo().setSerialNumber(vehicle.getSerialNumber());
//        return vehicleDetailDTO;
//    }
    public static VehicleDetailDTO toVehicleDetailDto(VehicleInfo vehicleDetail) {
        VehicleDetailDTO vehicleDetailDTO = MODEL_MAPPER.map(vehicleDetail, VehicleDetailDTO.class);
//        vehicleDetailDTO.setPurchaseInfo(MODEL_MAPPER.map(vehicleDetail, PurchaseInfo.class));
//        vehicleDetailDTO.getPurchaseInfo().setSerialNumber(vehicleDetail.getPurchaseInfo().getSerialNumber());
        return vehicleDetailDTO;
    }

    /**
     * @param vehicle
     * @return
     */
    public static VehicleDetailDTO toVehicleDetailDto(Vehicle vehicle) {
//        VehicleDetailDTO vehicleDetail = toVehicleDetailDto(vehicle.getVehicleDetail());
        VehicleDetailDTO vehicleDetail = toVehicleDetailDto(vehicle.getVehicleInfo());
        vehicleDetail.getPurchaseInfo().setSerialNumber(vehicle.getSerialNumber());
        return vehicleDetail;
    }

    /**
     * @param locationHistory
     * @return
     */
    public static LocationHistoryDTO toLocationHistoryDto(LocationHistory locationHistory) {
        LocationHistoryDTO locationHistoryDTO = MODEL_MAPPER.map(locationHistory, LocationHistoryDTO.class);
        locationHistoryDTO.setLastRecordedAt(TimeUtils.toLocalDateTime(locationHistory.getUpdatedAt()));
        return locationHistoryDTO;
    }

    /**
     * Converts the Vehicle entity object to a VehicleWithLocationDTO.
     *
     * @param vehicle the Vehicle entity object
     * @return VehicleWithLocationDTO
     */
    public static VehicleWithLocationDTO toVehicleWithLocationDto(Vehicle vehicle) {
        VehicleWithLocationDTO vehicleWithLocationDTO = MODEL_MAPPER.map(vehicle, VehicleWithLocationDTO.class);
        vehicleWithLocationDTO.setVehicleInfo(toVehicleDetailDto(vehicle));
//        vehicleWithLocationDTO.setVin(vehicle.getVehicleDetail().getVin());
        vehicleWithLocationDTO.setInUse(vehicle.isInUse());

        // get the location history info (already sorted, descending by timestamp)
        if (!vehicle.getLocationHistories().isEmpty()) {
            LocationHistory locationHistory = vehicle.getLocationHistories().get(0);
//            vehicleWithLocationDTO.setLastRecordedAt(TimeUtils.toLocalDateTime(locationHistory.getUpdatedAt()));
            vehicleWithLocationDTO.setTimestamp(TimeUtils.toLocalDateTime(locationHistory.getUpdatedAt()));
//            vehicleWithLocationDTO.setLastRideStart(locationHistory.getLastRideStart());
//            vehicleWithLocationDTO.setLastRideEnd(locationHistory.getLastRideEnd());
            vehicleWithLocationDTO.setLastLatitude(locationHistory.getLatitude());
            vehicleWithLocationDTO.setLastLongitude(locationHistory.getLongitude());
        }

        return vehicleWithLocationDTO;
    }

    /**
     * Converts the VehicleWithLocation entity object to a VehicleWithLocationDTO.
     *
     * @param vehicleWithLocation the Vehicle entity object
     * @return VehicleWithLocationDTO
     */
    public static VehicleWithLocationDTO toVehicleWithLocationDTO(VehicleWithLocation vehicleWithLocation) {
        VehicleWithLocationDTO
            vehicleWithLocationDTO =
            MODEL_MAPPER.map(vehicleWithLocation, VehicleWithLocationDTO.class);
//        vehicleWithLocationDTO.setVehicleInfo(toVehicleDetailDto(vehicleWithLocation.getVehicleDetail()));
//        vehicleWithLocationDTO.setVehicleDetail(new JSONObject(vehicleWithLocation.getVehicleDetail()).toMap());

        return vehicleWithLocationDTO;
    }

    /**
     * @param locationHistories
     * @return
     */
    public static List<LocationHistoryDTO> fromLocationHistory(List<LocationHistory> locationHistories) {
        List<LocationHistoryDTO> locationHistoryDTOList = new ArrayList<>();
        if (BeanUtils.isNotEmpty(locationHistories)) {
            locationHistoryDTOList = locationHistories.stream()
                .map(locationHistory -> toLocationHistoryDto(locationHistory))
                .collect(Collectors.toList());
        }

        return locationHistoryDTOList;
    }

    /**
     * Converts the Vehicle entity object to a VehicleWithHistoryDTO.
     *
     * @param vehicle the Vehicle entity object
     * @return VehicleWithHistoryDTO
     */
    public static VehicleWithHistoryDTO toVehicleWithHistoryDTO(Vehicle vehicle) {
        VehicleWithHistoryDTO vehicleWithLocationDTO = MODEL_MAPPER.map(vehicle, VehicleWithHistoryDTO.class);
//        vehicleWithLocationDTO.setVehicleInfo(toVehicleDetailDto(vehicle));
//        vehicleWithLocationDTO.setVin(vehicle.getVehicleDetail().getVin());
        vehicleWithLocationDTO.setInUse(vehicle.isInUse());
        vehicleWithLocationDTO.setLocationDetails(fromLocationHistory(vehicle.getLocationHistories()));
//        vehicleWithLocationDTO.setVehicleDetail(new JSONObject(vehicle.getVehicleDetail()).toMap());
        return vehicleWithLocationDTO;
    }

    /**
     * @param message
     * @return
     */
    public static AbstractResponse buildResponse(String message) {
        AbstractResponse abstractResponse = AbstractResponse.builder().build();
        abstractResponse.addMessage(message);
        return abstractResponse;
    }
}
