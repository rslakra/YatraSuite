package com.rslakra.microservice.yatrasuite.vehicleservice.controller;

import static com.rslakra.appsuite.core.RandomUtils.nextRandomId;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rslakra.microservice.yatrasuite.common.YatraUtils;
import com.rslakra.microservice.yatrasuite.common.exception.InvalidUUIDException;
import com.rslakra.microservice.yatrasuite.common.exception.InvalidValueException;
import com.rslakra.microservice.yatrasuite.common.exception.InvalidVehicleStateException;
import com.rslakra.microservice.yatrasuite.common.exception.NotFoundException;
import com.rslakra.microservice.yatrasuite.framework.advice.AbstractResponse;
import com.rslakra.microservice.yatrasuite.vehicleservice.dto.NewVehicleDTO;
import com.rslakra.microservice.yatrasuite.vehicleservice.dto.VehicleDetailDTO;
import com.rslakra.microservice.yatrasuite.vehicleservice.dto.VehicleIdDTO;
import com.rslakra.microservice.yatrasuite.vehicleservice.dto.VehicleWithHistoryDTO;
import com.rslakra.microservice.yatrasuite.vehicleservice.dto.VehicleWithLocationDTO;
import com.rslakra.microservice.yatrasuite.vehicleservice.persistence.entity.Vehicle;
import com.rslakra.microservice.yatrasuite.vehicleservice.service.VehicleService;
import com.rslakra.microservice.yatrasuite.vehicleservice.util.VehicleTestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@JsonTest
class VehicleControllerTest {

    @Mock
    private VehicleService vehicleService;

    @Autowired
    private ObjectMapper mapper;
    private VehicleController vehicleController;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        vehicleController = new VehicleController(mapper, vehicleService);
    }

    @Test
    public void addVehicle_shouldThrowAnException_ifTheSerialNumberIsInvalid() {
        NewVehicleDTO newVehicle = VehicleTestUtils.createNewVehicleDTO();
        newVehicle.setSerialNumber("BAD DATA");

        assertThrows(InvalidValueException.class, () -> {
            vehicleController.addVehicle(newVehicle).getBody();
        });

        verify(vehicleService, never()).addVehicle(VehicleTestUtils.anyBigDecimal(), VehicleTestUtils.anyBigDecimal(),
                                                   anyInt(), any());
    }

    @Test
    public void addVehicle_shouldThrowAnException_ifTheLatitudeIsInvalid() {
        NewVehicleDTO newVehicle = VehicleTestUtils.createNewVehicleDTO();
        newVehicle.setLatitude("BAD DATA");

        assertThrows(InvalidValueException.class, () -> {
            vehicleController.addVehicle(newVehicle).getBody();
        });

        verify(vehicleService, never()).addVehicle(VehicleTestUtils.anyBigDecimal(), VehicleTestUtils.anyBigDecimal(),
                                                   anyInt(), any());
    }

    @Test
    public void addVehicle_shouldThrowAnException_ifTheLongitudeIsInvalid() {
        NewVehicleDTO newVehicle = VehicleTestUtils.createNewVehicleDTO();
        newVehicle.setLongitude("BAD DATA");

        assertThrows(InvalidValueException.class, () -> {
            vehicleController.addVehicle(newVehicle).getBody();
        });

        verify(vehicleService, never()).addVehicle(VehicleTestUtils.anyBigDecimal(), VehicleTestUtils.anyBigDecimal(),
                                                   anyInt(), any());
    }

    @Test
    public void addVehicle_shouldThrowAnException_ifTheBatteryIsInvalid() {
        NewVehicleDTO newVehicle = VehicleTestUtils.createNewVehicleDTO();
        newVehicle.setBatteryLevel("BAD DATA");

        assertThrows(InvalidValueException.class, () -> {
            vehicleController.addVehicle(newVehicle).getBody();
        });

        verify(vehicleService, never()).addVehicle(VehicleTestUtils.anyBigDecimal(), VehicleTestUtils.anyBigDecimal(),
                                                   anyInt(), any());
    }

    @Test
    public void addVehicle_shouldCallAddVehicleOnTheService() throws InvalidValueException {
        NewVehicleDTO newVehicle = VehicleTestUtils.createNewVehicleDTO();
        Vehicle vehicle = VehicleTestUtils.createVehicle();
        VehicleDetailDTO expectedVehicleInfo = new VehicleDetailDTO(newVehicle);

        when(vehicleService
                 .addVehicle(VehicleTestUtils.anyBigDecimal(), VehicleTestUtils.anyBigDecimal(), anyInt(), any()))
            .thenReturn(vehicle);

        VehicleIdDTO response = vehicleController.addVehicle(newVehicle).getBody();

        ArgumentCaptor<VehicleDetailDTO> captor = ArgumentCaptor.forClass(VehicleDetailDTO.class);

        assertEquals(vehicle.getId().toString(), response.getId().toString());
        verify(vehicleService).addVehicle(
            eq(YatraUtils.asBigDecimalLatitude(newVehicle.getLatitude())),
            eq(YatraUtils.asBigDecimalLongitude(newVehicle.getLongitude())),
            eq(Integer.parseInt(newVehicle.getBatteryLevel())),
            captor.capture()
        );

        VehicleDetailDTO vehicleDetailDTO = captor.getValue();
        assertEquals(expectedVehicleInfo.toJsonString(), vehicleDetailDTO.toJsonString());
    }


    @Test
    public void getVehiclesWithLocation_shouldReturnTheVehicles_ifTheyExist() {
        int numVehicles = 10;

        List<Vehicle> expectedVehicles = Stream.generate(() -> VehicleTestUtils.createVehicle())
            .limit(numVehicles)
            .collect(Collectors.toList());

        when(vehicleService.getVehicles(numVehicles)).thenReturn(expectedVehicles);

        ResponseEntity<List<VehicleWithLocationDTO>> response = vehicleController.getVehiclesWithLocation(numVehicles);
        List<VehicleWithLocationDTO> vehicles = response.getBody();

        assertEquals(numVehicles, vehicles.size());
        for (int i = 0; i < numVehicles; i++) {
            Vehicle expected = expectedVehicles.get(i);
            VehicleWithLocationDTO vehicle = vehicles.get(i);
            assertEquals(expected.getId(), vehicle.getId());
            assertEquals(expected.getBatteryLevel(), vehicle.getBattery());
            assertEquals(expected.getSerialNumber(), String.valueOf(vehicle.getSerialNumber()));
            assertEquals(expected.isInUse(), vehicle.isInUse());
        }
    }

//    @Test
//    public void getVehiclesWithLocation_shouldReturnTheVehicles_ifTheyExist() {
//        int numVehicles = 10;
//
//        List<VehicleWithLocation> expectedVehicles = Stream.generate(() -> VehicleTestUtils.createVehicleWithLocation())
//            .limit(numVehicles)
//            .collect(Collectors.toList());
//
//        when(vehicleService.getVehicles(numVehicles)).thenReturn(expectedVehicles);
//
//        ResponseEntity<List<VehicleWithLocationDTO>> response = vehicleController.getVehiclesWithLocation(numVehicles);
//
//        List<VehicleWithLocationDTO> vehicles = response.getBody();
//
//        assertEquals(numVehicles, vehicles.size());
//        for (int i = 0; i < numVehicles; i++) {
//            VehicleWithLocation expected = expectedVehicles.get(i);
//            VehicleWithLocationDTO vehicle = vehicles.get(i);
//            assertEquals(expected.getId(), vehicle.getId());
//            assertEquals(expected.getBattery(), vehicle.getBattery());
//            assertEquals(expected.getSerialNumber(), String.valueOf(vehicle.getSerialNumber()));
//            assertEquals(expected.isInUse(), vehicle.isInUse());
//        }
//    }

    @Test
    public void getVehicleWithLocation_shouldReturnTheVehicle_ifItExists()
        throws NotFoundException, InvalidUUIDException {
        Vehicle expected = VehicleTestUtils.createVehicle();

        when(vehicleService.getVehicle(expected.getId())).thenReturn(expected);

        VehicleWithLocationDTO
            vehicle =
            vehicleController.getVehicleWithLocation(expected.getId().toString()).getBody();

        assertEquals(expected.getId(), vehicle.getId());
        assertEquals(expected.getBatteryLevel(), vehicle.getBattery());
        assertEquals(expected.getSerialNumber(), vehicle.getSerialNumber());
        assertEquals(expected.isInUse(), vehicle.isInUse());
        assertEquals(expected.getLocationHistories().get(0).getLatitude(), vehicle.getLastLatitude());
        assertEquals(expected.getLocationHistories().get(0).getLongitude(), vehicle.getLastLongitude());
//        assertEquals(expected.getLocationHistories().get(0).getLastRecordedAt(), vehicle.getLastRecordedAt());
    }

    @Test
    public void getVehicleWithHistory_shouldReturnTheVehicle_ifItExists()
        throws NotFoundException, InvalidUUIDException {
        Vehicle expected = VehicleTestUtils.createVehicle();

        when(vehicleService.getVehicle(expected.getId())).thenReturn(expected);

        VehicleWithHistoryDTO
            vehicle =
            vehicleController.getVehicleLocationHistory(expected.getId().toString()).getBody();

        assertEquals(expected.getId(), vehicle.getId());
        assertEquals(expected.getBatteryLevel(), vehicle.getBattery());
        assertEquals(expected.getSerialNumber(), vehicle.getSerialNumber());
        assertEquals(expected.isInUse(), vehicle.isInUse());
        assertEquals(expected.getLocationHistories().size(), vehicle.getLocationDetails().size());
    }

    @Test
    public void removeVehicle_shouldRemoveTheVehicle_ifItExists() throws NotFoundException,
                                                                         InvalidVehicleStateException,
                                                                         InvalidUUIDException {
//        UUID vehicleId = VehicleTestUtils.createVehicleId();
        Long vehicleId = nextRandomId();

        AbstractResponse abstractResponse = vehicleController.removeVehicle(vehicleId.toString()).getBody();

        assertEquals("Deleted vehicle with id <" + vehicleId + "> from database.",
                     abstractResponse.getMessages().get(0));
    }
}