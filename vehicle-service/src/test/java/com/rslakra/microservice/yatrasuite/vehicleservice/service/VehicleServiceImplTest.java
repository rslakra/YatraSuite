package com.rslakra.microservice.yatrasuite.vehicleservice.service;

import static com.devamatre.appsuite.core.RandomUtils.nextDateTime;
import static com.devamatre.appsuite.core.RandomUtils.nextRandomUuid;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.rslakra.microservice.yatrasuite.common.exception.InvalidValueException;
import com.rslakra.microservice.yatrasuite.common.exception.InvalidVehicleStateException;
import com.rslakra.microservice.yatrasuite.common.exception.NotFoundException;
import com.rslakra.microservice.yatrasuite.vehicleservice.dto.VehicleDetailDTO;
import com.rslakra.microservice.yatrasuite.vehicleservice.persistence.entity.LocationHistory;
import com.rslakra.microservice.yatrasuite.vehicleservice.persistence.entity.Vehicle;
import com.rslakra.microservice.yatrasuite.vehicleservice.persistence.entity.VehicleWithLocation;
import com.rslakra.microservice.yatrasuite.vehicleservice.persistence.repository.LocationHistoryRepository;
import com.rslakra.microservice.yatrasuite.vehicleservice.persistence.repository.VehicleRepository;
import com.rslakra.microservice.yatrasuite.vehicleservice.persistence.repository.VehicleWithLocationRepository;
import com.rslakra.microservice.yatrasuite.vehicleservice.service.impl.VehicleServiceImpl;
import com.rslakra.microservice.yatrasuite.vehicleservice.util.VehicleTestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Unit Tests for the VehicleServiceImpl.class
 */
public class VehicleServiceImplTest {

    @Mock
    private VehicleRepository vehicleRepository;

    @Mock
    private VehicleWithLocationRepository vehicleWithLocationRepository;

    @Mock
    private LocationHistoryRepository locationHistoryRepository;

    private VehicleService vehicleService;

    @BeforeEach
    public void init() {

        MockitoAnnotations.openMocks(this);
        vehicleService = new VehicleServiceImpl(
            vehicleRepository,
            vehicleWithLocationRepository,
            locationHistoryRepository);
    }

    @Test
    void getVehicle_shouldThrowAnException_ifTheVehicleDoesntExist() {
        UUID id = nextRandomUuid();

        when(vehicleRepository.findById(id)).thenReturn(Optional.empty());

        Exception exception = assertThrows(NotFoundException.class, () -> {
            vehicleService.getVehicle(id);
        });

        assertEquals("Vehicle id <" + id + "> not found", exception.getMessage());
        verify(vehicleRepository).findById(id);
    }

    @Test
    void getVehicle_shouldReturnTheVehicle_ifItExists() throws NotFoundException {
        Vehicle expected = VehicleTestUtils.createVehicle();

        when(vehicleRepository.findById(expected.getId())).thenReturn(Optional.of(expected));

        Vehicle vehicle = vehicleService.getVehicle(expected.getId());

        assertEquals(expected.getId(), vehicle.getId());
        assertEquals(expected.getSerialNumber(), vehicle.getSerialNumber());
        assertEquals(expected.getBatteryLevel(), vehicle.getBatteryLevel());
        assertEquals(expected.isInUse(), vehicle.isInUse());
        assertEquals(expected.getLocationHistories().get(0), vehicle.getLocationHistories().get(0));
        assertEquals(expected.getVehicleInfo(), vehicle.getVehicleInfo());
        verify(vehicleRepository).findById(expected.getId());
    }

    @Test
    void getVehiclesWithLocation_shouldReturnAnEmptyList_ifThereAreNoVehicles() {
        int numVehicles = 20;

//        when(vehicleWithLocationRepository.getVehiclesWithLocation(numVehicles)).thenReturn(new ArrayList<>());
//
//        List<VehicleWithLocation> vehicles = vehicleService.getVehiclesWithLocation(numVehicles);
//
//        assertEquals(0, vehicles.size());
//        verify(vehicleWithLocationRepository).getVehiclesWithLocation(numVehicles);
    }

    @Test
    void getVehiclesWithLocation_shouldReturnTheVehicles_ifTheyExist() {
        int numVehicles = 20;
        List<VehicleWithLocation> expected = Stream.generate(() -> VehicleTestUtils.createVehicleWithLocation())
            .limit(numVehicles)
            .collect(Collectors.toList());

//        when(vehicleWithLocationRepository.getVehiclesWithLocation(numVehicles)).thenReturn(expected);
//
//        List<VehicleWithLocation> vehicles = vehicleService.getVehiclesWithLocation(numVehicles);
//
//        verify(vehicleWithLocationRepository).getVehiclesWithLocation(numVehicles);
//        for (int i = 0; i < numVehicles; i++) {
//            assertEquals(expected.get(i).getId(), vehicles.get(i).getId());
//        }
    }

//    @Test
//    void getVehiclesWithLocation_shouldRequestTheMaximum_ifNumRecordsIsNull() {
//        List<VehicleWithLocation> vehicles = vehicleService.getVehicles(null);
//        verify(vehicleWithLocationRepository).getVehiclesWithLocation(VehicleService.MAX_VEHICLES_TO_RETURN);
//    }

    @Test
    void addVehicle_shouldSaveTheVehicleAndLocationHistory() throws InvalidValueException {
        Vehicle expectedVehicle = VehicleTestUtils.createVehicle();
        LocationHistory expectedLocation = VehicleTestUtils.createLocationHistory(expectedVehicle);
        VehicleDetailDTO expectedVehicleDetailDTO = VehicleTestUtils.createVehicleInfoDTO();

        vehicleService.addVehicle(
            expectedLocation.getLatitude(),
            expectedLocation.getLongitude(),
            expectedVehicle.getBatteryLevel(),
            expectedVehicleDetailDTO
        );

        ArgumentCaptor<Vehicle> vehicleArgumentCaptor =
            ArgumentCaptor.forClass(Vehicle.class);
        ArgumentCaptor<LocationHistory> locationHistoryArgumentCaptor =
            ArgumentCaptor.forClass(LocationHistory.class);

        verify(vehicleRepository).save(vehicleArgumentCaptor.capture());
        verify(locationHistoryRepository).save(locationHistoryArgumentCaptor.capture());

        Vehicle vehicle = vehicleArgumentCaptor.getValue();
        assertEquals(expectedVehicle.getBatteryLevel(), vehicle.getBatteryLevel());
        assertEquals(expectedVehicleDetailDTO.toJsonString(), vehicle.getVehicleInfo());
        LocationHistory locationHistory = locationHistoryArgumentCaptor.getValue();
        assertEquals(expectedLocation.getLatitude(), locationHistory.getLatitude());
        assertEquals(expectedLocation.getLongitude(), locationHistory.getLongitude());
    }

    @Test
    public void removeVehicle_shouldThrowAnException_IfTheVehicleDoesntExist() {
        UUID vehicleId = nextRandomUuid();
        when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            vehicleService.removeVehicle(vehicleId);
        });

        verify(vehicleRepository, never()).delete(any());
    }

    @Test
    public void removeVehicle_shouldThrowAnException_IfTheVehicleIsInUse() {
        Vehicle vehicle = VehicleTestUtils.createVehicle();
//        vehicle.setLastRideStart(nextDateTime());
//        vehicle.setLastRideEnd(null);

        when(vehicleRepository.findById(vehicle.getId())).thenReturn(Optional.of(vehicle));

        assertThrows(InvalidVehicleStateException.class, () -> {
            vehicleService.removeVehicle(vehicle.getId());
        });

        verify(vehicleRepository, never()).delete(any());
    }

    @Test
    public void removeVehicle_shouldDeleteTheVehicle_IfTheVehicleExistsAndIsValid()
        throws NotFoundException, InvalidVehicleStateException {
        Vehicle vehicle = VehicleTestUtils.createVehicle();

        when(vehicleRepository.findById(vehicle.getId())).thenReturn(Optional.of(vehicle));

        vehicleService.removeVehicle(vehicle.getId());

        verify(vehicleRepository).delete(vehicle);
    }

    @Test
    public void checkoutVehicle_shouldThrowAnException_ifTheVehicleDoesntExist() {
        Vehicle vehicle = VehicleTestUtils.createVehicle();

        when(vehicleRepository.findById(vehicle.getId())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            LocalDateTime lastRideStart = LocalDateTime.now();
            LocationHistory locationHistory = vehicle.getLastLocationHistory();
//            if (BeanUtils.isNotNull(locationHistory)) {
//                lastRideStart = locationHistory.getLastRideStart();
//            }
            vehicleService.checkoutVehicle(vehicle.getId(), lastRideStart);
        });

        verify(vehicleRepository, never()).save(any());
    }

    @Test
    public void checkoutVehicle_shouldUpdateTheLastRideStart_ifThereIsNoPreviousRide()
        throws NotFoundException, InvalidVehicleStateException {
        Vehicle expectedVehicle = VehicleTestUtils.createVehicle();
        LocalDateTime startTime = nextDateTime();
//        expectedVehicle.setLastRideStart(null);
//        expectedVehicle.setLastRideEnd(null);

        when(vehicleRepository.findById(expectedVehicle.getId())).thenReturn(Optional.of(expectedVehicle));

        Vehicle vehicle = vehicleService.checkoutVehicle(expectedVehicle.getId(), startTime);

        assertEquals(expectedVehicle.getId(), vehicle.getId());
        assertEquals(true, vehicle.isInUse());
//        assertEquals(startTime, vehicle.getLastRideStart());
//        assertNull(vehicle.getLastRideEnd());
        verify(vehicleRepository).save(vehicle);
    }

    @Test
    public void checkoutVehicle_shouldUpdateTheLastRideStart_ifThereIsAnEarlierRide()
        throws NotFoundException, InvalidVehicleStateException {
        Vehicle expectedVehicle = VehicleTestUtils.createVehicle();
        LocalDateTime earlierTime = nextDateTime();
        LocalDateTime laterTime = nextDateTime().plusSeconds(1000);
//        expectedVehicle.setLastRideStart(earlierTime);
//        expectedVehicle.setLastRideEnd(earlierTime);

        when(vehicleRepository.findById(expectedVehicle.getId())).thenReturn(Optional.of(expectedVehicle));

        Vehicle vehicle = vehicleService.checkoutVehicle(expectedVehicle.getId(), laterTime);

        assertEquals(expectedVehicle.getId(), vehicle.getId());
        assertEquals(true, vehicle.isInUse());
//        assertEquals(laterTime, vehicle.getLastRideStart());
//        assertEquals(earlierTime, vehicle.getLastRideEnd());
        verify(vehicleRepository).save(vehicle);
    }

    @Test
    public void checkoutVehicle_shouldNotUpdateTheLastRideStart_ifThereIsALaterRide()
        throws NotFoundException, InvalidVehicleStateException {
        Vehicle expectedVehicle = VehicleTestUtils.createVehicle();
        LocalDateTime earlierTime = nextDateTime();
        LocalDateTime laterTime = nextDateTime().plusSeconds(1000);
//        expectedVehicle.setLastRideStart(laterTime);
//        expectedVehicle.setLastRideEnd(laterTime);

        when(vehicleRepository.findById(expectedVehicle.getId())).thenReturn(Optional.of(expectedVehicle));

        Vehicle vehicle = vehicleService.checkoutVehicle(expectedVehicle.getId(), earlierTime);

        assertEquals(expectedVehicle.getId(), vehicle.getId());
        assertEquals(false, vehicle.isInUse());
//        assertEquals(laterTime, vehicle.getLastRideStart());
//        assertEquals(laterTime, vehicle.getLastRideEnd());
        verify(vehicleRepository).save(vehicle);
    }

    @Test
    public void checkinVehicle_shouldThrowAnException_ifTheVehicleDoesntExist() throws InvalidValueException {
        Vehicle vehicle = VehicleTestUtils.createVehicle();
        LocationHistory locationHistory = VehicleTestUtils.createLocationHistory(vehicle);

        when(vehicleRepository.findById(vehicle.getId())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            vehicleService.checkinVehicle(
                vehicle.getId(),
                locationHistory.getLatitude(),
                locationHistory.getLongitude(),
                vehicle.getBatteryLevel(), LocalDateTime.now()
            );
        });

        verify(vehicleRepository, never()).save(any());
    }

    @Test
    public void checkinVehicle_shouldMarkTheVehicleNotInUseAndAddLocationHistory()
        throws NotFoundException, InvalidVehicleStateException, InvalidValueException {
        Vehicle expectedVehicle = VehicleTestUtils.createVehicle();
//        expectedVehicle.setLastRideStart(nextDateTime());
//        expectedVehicle.setLastRideEnd(null);
        LocationHistory expectedLocationHistory = VehicleTestUtils.createLocationHistory(expectedVehicle);

        when(vehicleRepository.findById(expectedVehicle.getId())).thenReturn(Optional.of(expectedVehicle));

        Vehicle vehicle = vehicleService.checkinVehicle(
            expectedVehicle.getId(),
            expectedLocationHistory.getLatitude(),
            expectedLocationHistory.getLongitude(),
            expectedVehicle.getBatteryLevel(), LocalDateTime.now()
        );

        verify(vehicleRepository).save(vehicle);

        assertEquals(expectedVehicle.getId(), vehicle.getId());
        assertEquals(false, vehicle.isInUse());

        ArgumentCaptor<LocationHistory> locationHistoryArgumentCaptor =
            ArgumentCaptor.forClass(LocationHistory.class);

        verify(locationHistoryRepository).save(locationHistoryArgumentCaptor.capture());
        LocationHistory locationHistory = locationHistoryArgumentCaptor.getValue();
        assertEquals(expectedLocationHistory.getLatitude(), locationHistory.getLatitude());
        assertEquals(expectedLocationHistory.getLongitude(), locationHistory.getLongitude());
//        assertEquals(expectedLocationHistory.getLastRecordedAt(), locationHistory.getLastRecordedAt());
        assertEquals(expectedLocationHistory.getVehicle().getId(), locationHistory.getVehicle().getId());
    }
}
