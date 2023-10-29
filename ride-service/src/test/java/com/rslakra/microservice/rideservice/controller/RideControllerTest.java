package com.rslakra.microservice.rideservice.controller;

import static com.rslakra.frameworks.core.RandomUtils.nextDateTime;
import static com.rslakra.frameworks.core.RandomUtils.nextRandomEmail;
import static com.rslakra.frameworks.core.RandomUtils.nextRandomUuid;
import static com.rslakra.frameworks.spring.unittest.TestUtils.anyBigDecimal;
import static com.rslakra.microservice.rideservice.util.RideTestUtils.createEndRideRequest;
import static com.rslakra.microservice.rideservice.util.RideTestUtils.createRide;
import static com.rslakra.microservice.rideservice.util.RideTestUtils.createRideRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.rslakra.microservice.common.Constants;
import com.rslakra.microservice.common.exception.InvalidUUIDException;
import com.rslakra.microservice.common.exception.InvalidValueException;
import com.rslakra.microservice.common.exception.InvalidVehicleStateException;
import com.rslakra.microservice.common.exception.NotFoundException;
import com.rslakra.microservice.rideservice.dto.EndRideRequestDto;
import com.rslakra.microservice.rideservice.dto.RideDto;
import com.rslakra.microservice.rideservice.dto.RideRequestDto;
import com.rslakra.microservice.rideservice.dto.RideResponseDto;
import com.rslakra.microservice.rideservice.persistence.entity.Ride;
import com.rslakra.microservice.rideservice.service.RideService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

class RideControllerTest {

    @Mock
    private RideService rideService;

    private RideController rideController;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        rideController = new RideController(rideService);
    }

    @Test
    public void getActiveRide_shouldThrowAnException_ifTheVehicleIdIsInvalid() throws NotFoundException {
        String vehicleId = "INVALID";
        String email = nextRandomEmail();

        assertThrows(InvalidUUIDException.class, () -> {
            rideController.getActiveRide(vehicleId, email);
        });

        verify(rideService, never()).getActiveRides(any(), any());
    }

    @Test
    public void getActiveRide_shouldThrowAnException_ifTheRideIsNotFound() throws NotFoundException {
        UUID vehicleId = nextRandomUuid();
        String email = nextRandomEmail();

        when(rideService.getActiveRides(email, vehicleId)).thenThrow(new NotFoundException("Not Found"));

        assertThrows(NotFoundException.class, () -> {
            rideController.getActiveRide(vehicleId.toString(), email);
        });
    }

    @Test
    public void getActiveRide_shouldReturnTheRide_ifItExists() throws NotFoundException, InvalidUUIDException {
        Ride ride = createRide();

        when(rideService.getActiveRides(ride.getUserEmail(), ride.getVehicleId())).thenReturn(ride);

        RideDto dto = rideController.getActiveRide(ride.getVehicleId().toString(), ride.getUserEmail()).getBody();

        assertEquals(ride.getId(), dto.getId());
        assertEquals(ride.getVehicleId(), dto.getVehicleId());
        assertEquals(ride.getUserEmail(), dto.getUserEmail());
        assertEquals(ride.getStartTime(), dto.getStartTime());
        assertEquals(ride.getEndTime(), dto.getEndTime());
    }

    @Test
    public void startRide_shouldThrowAnException_ifTheVehicleIdIsInvalid() throws InvalidVehicleStateException {
        RideRequestDto request = createRideRequest();
        request.setVehicleId("INVALID");

        assertThrows(InvalidUUIDException.class, () -> {
            rideController.startRide(request);
        });

        verify(rideService, never()).startRide(any(), any(), any());
    }

    @Test
    public void startRide_shouldStartTheRide() throws InvalidUUIDException, InvalidVehicleStateException {
        Ride ride = createRide();
        RideRequestDto request = createRideRequest(ride);
        LocalDateTime curTime = nextDateTime();

        when(rideService.startRide(eq(UUID.fromString(request.getVehicleId())), eq(request.getEmail()), any()))
            .thenReturn(ride);

        RideResponseDto response = rideController.startRide(request).getBody();

        ArgumentCaptor<LocalDateTime> tsCaptor = ArgumentCaptor.forClass(LocalDateTime.class);

        verify(rideService).startRide(eq(UUID.fromString(request.getVehicleId())), eq(request.getEmail()),
                                      tsCaptor.capture());

        assertTrue(tsCaptor.getValue().compareTo(curTime) >= 0);

        assertEquals(1, response.getMessages().length);
        assertEquals(String.format(Constants.MSG_RIDE_STARTED, request.getVehicleId()), response.getMessages()[0]);

        RideDto dto = response.getRideDTO();
        assertEquals(ride.getId(), dto.getId());
        assertEquals(ride.getVehicleId(), dto.getVehicleId());
        assertEquals(ride.getUserEmail(), dto.getUserEmail());
        assertEquals(ride.getStartTime(), dto.getStartTime());
        assertEquals(ride.getEndTime(), dto.getEndTime());
    }

    @Test
    public void endRide_shouldThrowAnException_ifTheVehicleIdIsInvalid() throws NotFoundException {
        EndRideRequestDto request = createEndRideRequest();
        request.setVehicleId("INVALID");

        assertThrows(InvalidUUIDException.class, () -> {
            rideController.endRide(request);
        });

        verify(rideService, never()).endRide(any(), any(), anyInt(), anyBigDecimal(), anyBigDecimal(), any());
    }

    @Test
    public void endRide_shouldThrowAnException_ifTheBatteryIsInvalid() throws NotFoundException {
        EndRideRequestDto request = createEndRideRequest();
        request.setBattery("INVALID");

        assertThrows(InvalidValueException.class, () -> {
            rideController.endRide(request);
        });

        verify(rideService, never()).endRide(any(), any(), anyInt(), anyBigDecimal(), anyBigDecimal(), any());
    }

    @Test
    public void endRide_shouldThrowAnException_ifTheLatitudeIsInvalid() throws NotFoundException {
        EndRideRequestDto request = createEndRideRequest();
        request.setLatitude("INVALID");

        assertThrows(InvalidValueException.class, () -> {
            rideController.endRide(request);
        });

        verify(rideService, never()).endRide(any(), any(), anyInt(), anyBigDecimal(), anyBigDecimal(), any());
    }

    @Test
    public void endRide_shouldThrowAnException_ifTheLongitudeIsInvalid() throws NotFoundException {
        EndRideRequestDto request = createEndRideRequest();
        request.setLongitude("INVALID");

        assertThrows(InvalidValueException.class, () -> {
            rideController.endRide(request);
        });

        verify(rideService, never()).endRide(any(), any(), anyInt(), anyBigDecimal(), anyBigDecimal(), any());
    }

    @Test
    public void endRide_shouldThrowAnException_ifNoActiveRideExists() throws NotFoundException {
        EndRideRequestDto request = createEndRideRequest();

        when(rideService.endRide(
            eq(UUID.fromString(request.getVehicleId())),
            eq(request.getEmail()),
            eq(Integer.parseInt(request.getBattery())),
            eq(new BigDecimal(request.getLatitude())),
            eq(new BigDecimal(request.getLongitude())),
            any())
        ).thenThrow(new NotFoundException("Not Found"));

        assertThrows(NotFoundException.class, () -> {
            rideController.endRide(request);
        });
    }

    @Test
    public void endRide_shouldEndTheRide_ifItExists()
        throws NotFoundException, InvalidUUIDException, InvalidValueException {
        Ride ride = createRide();
        EndRideRequestDto request = createEndRideRequest(ride);
        LocalDateTime curTime = nextDateTime();

        when(rideService.endRide(
            eq(UUID.fromString(request.getVehicleId())),
            eq(request.getEmail()),
            eq(Integer.parseInt(request.getBattery())),
            eq(new BigDecimal(request.getLatitude())),
            eq(new BigDecimal(request.getLongitude())),
            any())
        ).thenReturn(ride);

        RideResponseDto response = rideController.endRide(request).getBody();

        ArgumentCaptor<LocalDateTime> tsCaptor = ArgumentCaptor.forClass(LocalDateTime.class);

        verify(rideService).endRide(
            eq(UUID.fromString(request.getVehicleId())),
            eq(request.getEmail()),
            eq(Integer.parseInt(request.getBattery())),
            eq(new BigDecimal(request.getLatitude())),
            eq(new BigDecimal(request.getLongitude())),
            tsCaptor.capture());

        assertTrue(tsCaptor.getValue().compareTo(curTime) >= 0);

        assertEquals(1, response.getMessages().length);
        assertEquals(String.format(Constants.MSG_RIDE_ENDED, request.getVehicleId()), response.getMessages()[0]);

        RideDto dto = response.getRideDTO();
        assertEquals(ride.getId(), dto.getId());
        assertEquals(ride.getVehicleId(), dto.getVehicleId());
        assertEquals(ride.getUserEmail(), dto.getUserEmail());
        assertEquals(ride.getStartTime(), dto.getStartTime());
        assertEquals(ride.getEndTime(), dto.getEndTime());
    }

}