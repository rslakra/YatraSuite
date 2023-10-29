package com.rslakra.microservice.rideservice.service;

import static com.rslakra.frameworks.core.RandomUtils.nextDateTime;
import static com.rslakra.frameworks.core.RandomUtils.nextLongitudeBigDecimal;
import static com.rslakra.frameworks.core.RandomUtils.nextRandomBigDecimal;
import static com.rslakra.frameworks.core.RandomUtils.nextRandomEmail;
import static com.rslakra.frameworks.core.RandomUtils.nextRandomPercentage;
import static com.rslakra.frameworks.core.RandomUtils.nextRandomUuid;
import static com.rslakra.microservice.rideservice.util.RideTestUtils.createRide;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.rslakra.microservice.common.exception.InvalidVehicleStateException;
import com.rslakra.microservice.common.exception.NotFoundException;
import com.rslakra.microservice.rideservice.kafka.event.EventPublisher;
import com.rslakra.microservice.rideservice.persistence.entity.Ride;
import com.rslakra.microservice.rideservice.persistence.repository.RideRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Unit Tests for RideServiceImpl.class
 */

public class RideServiceImplTest {

    @Mock
    private RideRepository rideRepository;
    @Mock
    private EventPublisher eventPublisher;

    private RideService rideService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        rideService = new RideServiceImpl(rideRepository, eventPublisher);
    }

    @Test
    public void getActiveRide_shouldThrowAnException_ifTheRideDoesntExist() {
        String email = nextRandomEmail();
        UUID vehicleId = nextRandomUuid();

        when(rideRepository.getActiveRides(email, vehicleId)).thenReturn(Collections.EMPTY_LIST);

        assertThrows(NotFoundException.class, () -> {
            rideService.getActiveRides(email, vehicleId);
        });
    }

    @Test
    public void getActiveRide_shouldReturnTheRide_ifItExists() throws NotFoundException {
        Ride expected = createRide();

        when(rideRepository.getActiveRides(expected.getUserEmail(),
                                           expected.getVehicleId())).thenReturn(Collections.singletonList(expected));

        Ride ride = rideService.getActiveRides(expected.getUserEmail(), expected.getVehicleId());

        assertEquals(expected.getId(), ride.getId());
        assertEquals(expected.getUserEmail(), ride.getUserEmail());
        assertEquals(expected.getVehicleId(), ride.getVehicleId());
        assertEquals(expected.getStartTime(), ride.getStartTime());
        assertEquals(expected.getEndTime(), ride.getEndTime());
    }

    @Test
    public void getActiveRide_shouldReturnTheFirstRide_ifMultiplesExist() throws NotFoundException {
        Ride expected = createRide();
        Ride extra1 = createRide();
        Ride extra2 = createRide();

        when(rideRepository.getActiveRides(expected.getUserEmail(),
                                           expected.getVehicleId())).thenReturn(
            Arrays.asList(expected, extra1, extra2));

        Ride ride = rideService.getActiveRides(expected.getUserEmail(), expected.getVehicleId());

        assertEquals(expected.getId(), ride.getId());
        assertEquals(expected.getUserEmail(), ride.getUserEmail());
        assertEquals(expected.getVehicleId(), ride.getVehicleId());
        assertEquals(expected.getStartTime(), ride.getStartTime());
        assertEquals(expected.getEndTime(), ride.getEndTime());
    }

    @Test
    public void getRidesForUser_shouldReturnAnEmptyList_ifNoRidesExist() {
        String email = nextRandomEmail();

        when(rideRepository.findRidesByUser(email)).thenReturn(Collections.EMPTY_LIST);

        List<Ride> rides = rideService.getRidesByUser(email);
        assertEquals(0, rides.size());
    }

    @Test
    public void getRidesForUser_shouldReturnARide_ifItExists() {
        Ride expected = createRide();

        when(rideRepository.findRidesByUser(expected.getUserEmail())).thenReturn(Collections.singletonList(expected));

        List<Ride> rides = rideService.getRidesByUser(expected.getUserEmail());

        assertEquals(1, rides.size());
        assertEquals(expected.getId(), rides.get(0).getId());
        assertEquals(expected.getUserEmail(), rides.get(0).getUserEmail());
        assertEquals(expected.getVehicleId(), rides.get(0).getVehicleId());
        assertEquals(expected.getStartTime(), rides.get(0).getStartTime());
        assertEquals(expected.getEndTime(), rides.get(0).getEndTime());
    }

    @Test
    public void getRidesForUser_shouldAllRides_ifMultiplesExist() {
        Ride expected1 = createRide();
        Ride expected2 = createRide();
        Ride expected3 = createRide();

        when(rideRepository.findRidesByUser(expected1.getUserEmail())).thenReturn(
            Arrays.asList(expected1, expected2, expected3));

        List<Ride> rides = rideService.getRidesByUser(expected1.getUserEmail());

        assertEquals(3, rides.size());
        assertEquals(expected1.getId(), rides.get(0).getId());
        assertEquals(expected2.getId(), rides.get(1).getId());
        assertEquals(expected3.getId(), rides.get(2).getId());
    }

    @Test
    public void startRide_shouldCreateANewRide() throws InvalidVehicleStateException {
        UUID vehicleId = nextRandomUuid();
        String email = nextRandomEmail();
        LocalDateTime startTime = nextDateTime();

        when(rideRepository.save(any(Ride.class))).thenAnswer(input -> input.getArgument(0));

        Ride ride = rideService.startRide(vehicleId, email, startTime);

        verify(rideRepository).save(ride);

        assertNull(ride.getId()); // The ID is assigned during persistence which is mocked.
        assertEquals(vehicleId, ride.getVehicleId());
        assertEquals(email, ride.getUserEmail());
        assertEquals(startTime, ride.getStartTime());
        assertNull(ride.getEndTime());
    }

    @Test
    public void startRide_shouldThrowAnException_ifThereIsAnExistingRideForThatVehicle() {
        Ride existing = createRide();

        when(rideRepository.getActiveRidesByVehicle(existing.getVehicleId())).thenReturn(Arrays.asList(existing));

        assertThrows(InvalidVehicleStateException.class,
                     () -> rideService.startRide(existing.getVehicleId(), nextRandomEmail(), nextDateTime()));

        verify(rideRepository, never()).save(any());
    }

    @Test
    public void endRide_shouldThrowAnException_ifTheRideDoesntExist() {
        String email = nextRandomEmail();
        UUID vehicleId = nextRandomUuid();
        Integer battery = nextRandomPercentage();
        BigDecimal latitude = nextRandomBigDecimal();
        BigDecimal longitude = nextLongitudeBigDecimal();
        LocalDateTime endTime = nextDateTime();

        when(rideRepository.getActiveRides(email, vehicleId)).thenReturn(Collections.EMPTY_LIST);

        assertThrows(NotFoundException.class, () -> {
            rideService.endRide(vehicleId, email, battery, latitude, longitude, endTime);
        });

        verify(rideRepository, never()).save(any());
    }

    @Test
    public void endRide_shouldUpdateTheRide_ifItExists() throws NotFoundException {
        Integer battery = nextRandomPercentage();
        BigDecimal latitude = nextRandomBigDecimal();
        BigDecimal longitude = nextLongitudeBigDecimal();
        Ride existing = createRide();
        existing.setEndTime(null);
        LocalDateTime endTime = nextDateTime();

        when(rideRepository.getActiveRides(existing.getUserEmail(),
                                           existing.getVehicleId())).thenReturn(Collections.singletonList(existing));
        when(rideRepository.save(any(Ride.class))).thenAnswer(input -> input.getArgument(0));

        Ride
            updated =
            rideService.endRide(existing.getVehicleId(), existing.getUserEmail(), battery, latitude, longitude,
                                endTime);

        verify(rideRepository).save(updated);

        assertEquals(existing.getId(), updated.getId());
        assertEquals(existing.getVehicleId(), updated.getVehicleId());
        assertEquals(existing.getUserEmail(), updated.getUserEmail());
        assertEquals(existing.getStartTime(), updated.getStartTime());
        assertEquals(endTime, updated.getEndTime());
    }
}
