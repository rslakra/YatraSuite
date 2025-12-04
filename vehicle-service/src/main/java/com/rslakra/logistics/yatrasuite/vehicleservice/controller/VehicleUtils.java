package com.rslakra.logistics.yatrasuite.vehicleservice.controller;

import com.rslakra.appsuite.core.BeanUtils;
import com.rslakra.logistics.yatrasuite.framework.TimeUtils;
import com.rslakra.logistics.yatrasuite.framework.advice.AbstractResponse;
import com.rslakra.logistics.yatrasuite.vehicleservice.dto.*;
import com.rslakra.logistics.yatrasuite.vehicleservice.persistence.entity.LocationHistory;
import com.rslakra.logistics.yatrasuite.vehicleservice.persistence.entity.Vehicle;
import com.rslakra.logistics.yatrasuite.vehicleservice.persistence.entity.VehicleInfo;
import com.rslakra.logistics.yatrasuite.vehicleservice.persistence.entity.VehicleWithLocation;
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
     * Converts VehicleInfo entity to VehicleDetailDTO.
     *
     * @param vehicleInfo the VehicleInfo entity
     * @return VehicleDetailDTO with all fields properly mapped
     */
    public static VehicleDetailDTO toVehicleDetailDto(VehicleInfo vehicleInfo) {
        VehicleDetailDTO vehicleDetailDTO = new VehicleDetailDTO();
        vehicleDetailDTO.setType(vehicleInfo.getType());
        vehicleDetailDTO.setColor(vehicleInfo.getColor());
        vehicleDetailDTO.setWear(vehicleInfo.getWear());

        // Map nested purchaseInfo
        if (vehicleInfo.getPurchaseInfo() != null) {
            PurchaseInfo purchaseInfoDTO = new PurchaseInfo();
            purchaseInfoDTO.setSerialNumber(vehicleInfo.getPurchaseInfo().getSerialNumber());
            purchaseInfoDTO.setManufacturer(vehicleInfo.getPurchaseInfo().getManufacturer());
            purchaseInfoDTO.setPurchasedOn(vehicleInfo.getPurchaseInfo().getPurchasedOn());
            vehicleDetailDTO.setPurchaseInfo(purchaseInfoDTO);
        }

        return vehicleDetailDTO;
    }

    /**
     * @param vehicle
     * @return
     */
    public static VehicleDetailDTO toVehicleDetailDto(Vehicle vehicle) {
        //                VehicleDetailDTO vehicleDetail = toVehicleDetailDto(vehicle.getVehicleDetail());
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
        VehicleWithLocationDTO vehicleWithLocationDTO = new VehicleWithLocationDTO();
        vehicleWithLocationDTO.setId(vehicleWithLocation.getId());
        vehicleWithLocationDTO.setSerialNumber(vehicleWithLocation.getSerialNumber() != null ? vehicleWithLocation.getSerialNumber() : 0);
        vehicleWithLocationDTO.setBattery(vehicleWithLocation.getBatteryLevel() != null ? vehicleWithLocation.getBatteryLevel() : 0);
        vehicleWithLocationDTO.setInUse(vehicleWithLocation.isInUse());

        // Map vehicle info
        if (vehicleWithLocation.getVehicleInfo() != null) {
            vehicleWithLocationDTO.setVehicleInfo(toVehicleDetailDto(vehicleWithLocation.getVehicleInfo()));
        }

        // Map location fields (entity has latitude/longitude, DTO has lastLatitude/lastLongitude)
        vehicleWithLocationDTO.setLastLatitude(vehicleWithLocation.getLatitude());
        vehicleWithLocationDTO.setLastLongitude(vehicleWithLocation.getLongitude());
        vehicleWithLocationDTO.setTimestamp(vehicleWithLocation.getTimestamp());

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
        VehicleWithHistoryDTO vehicleWithHistoryDTO = new VehicleWithHistoryDTO();
        vehicleWithHistoryDTO.setId(vehicle.getId());
        vehicleWithHistoryDTO.setSerialNumber(vehicle.getSerialNumber() != null ? vehicle.getSerialNumber() : 0);
        vehicleWithHistoryDTO.setBattery(vehicle.getBatteryLevel() != null ? vehicle.getBatteryLevel() : 0);
        vehicleWithHistoryDTO.setInUse(vehicle.isInUse());

        // Map vehicle info
        if (vehicle.getVehicleInfo() != null) {
            vehicleWithHistoryDTO.setVehicleInfo(toVehicleDetailDto(vehicle.getVehicleInfo()));
        }

        // Map location history
        List<LocationHistoryDTO> locationHistoryDTOs = fromLocationHistory(vehicle.getLocationHistories());
        vehicleWithHistoryDTO.setLocationDetails(locationHistoryDTOs);

        // Set latest location at root level for convenience
        if (BeanUtils.isNotEmpty(locationHistoryDTOs)) {
            LocationHistoryDTO latestLocation = locationHistoryDTOs.get(0);
            vehicleWithHistoryDTO.setLastLatitude(latestLocation.getLatitude());
            vehicleWithHistoryDTO.setLastLongitude(latestLocation.getLongitude());
            vehicleWithHistoryDTO.setTimestamp(latestLocation.getLastRecordedAt());
        }

        return vehicleWithHistoryDTO;
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
