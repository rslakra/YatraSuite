package com.rslakra.microservice.yatrathymeleaf.controller;

import static com.rslakra.frameworks.core.RandomUtils.nextDateTime;
import static com.rslakra.frameworks.core.RandomUtils.nextRandomEmail;
import static com.rslakra.frameworks.core.RandomUtils.nextRandomUuid;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.rslakra.microservice.common.exception.InvalidUUIDException;
import com.rslakra.microservice.common.exception.InvalidVehicleStateException;
import com.rslakra.microservice.common.exception.NotFoundException;
import com.rslakra.microservice.yatrathymeleaf.dto.ride.Ride;
import com.rslakra.microservice.yatrathymeleaf.service.RideService;
import com.rslakra.microservice.yatrathymeleaf.util.TestHelpers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class RideControllerTest {

    private static final Long RIDE_ID = Long.valueOf(0);
    @Mock
    private RideService rideService;
    @InjectMocks
    private RideController rideController;
    private Model model;
    private Ride ride;

    /**
     *
     */
    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        model = (Model) new ModelMap();
        ride = new Ride();
        ride.setUserEmail("rslakra@lakra.com");
        ride.setVehicleId(nextRandomUuid());
        ride.setStartTime(nextDateTime());

        rideController = new RideController(rideService);
    }
//
//    @Test
//    public void startRide_shouldCallTheRideClient()
//        throws InvalidUUIDException, NotFoundException, InvalidVehicleStateException {
//        Map<String, Object> expectedRequest = TestHelpers.createArbitraryMap();
//        Map<String, Object> expectedResponse = TestHelpers.createArbitraryMap();
//
//        when(rideController.startRide(model, expectedRequest)).thenReturn(expectedResponse);
//
//        Map<String, Object> response = rideController.startRide(model,expectedRequest).getBody();
//
//        verify(mockRideClient).startRide(expectedRequest);
//
//        assertEquals(expectedResponse, response);
//    }
//
//    @Test
//    public void endRide_shouldCallTheRideClient() {
//        Map<String, Object> expectedRequest = TestHelpers.createArbitraryMap();
//        Map<String, Object> expectedResponse = TestHelpers.createArbitraryMap();
//
//        when(mockRideClient.endRide(expectedRequest)).thenReturn(expectedResponse);
//
//        Map<String, Object> response = rideController.endRide(expectedRequest).getBody();
//
//        verify(mockRideClient).endRide(expectedRequest);
//
//        assertEquals(expectedResponse, response);
//    }
//
//    @Test
//    public void getRides_shouldCallTheRideClient() {
//        String email = nextRandomEmail();
//        List<Map<String, Object>>
//            expectedResponse =
//            IntStream.range(0, 5).mapToObj(i -> TestHelpers.createArbitraryMap()).collect(Collectors.toList());
//
//        when(mockRideClient.getRides(email)).thenReturn(expectedResponse);
//
//        String response = rideController.getUserRides();
//
//        verify(mockRideClient).getRides(email);
//
//        assertEquals(expectedResponse, response);
//    }

}