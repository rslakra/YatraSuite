package com.rslakra.microservice.yatrawebapp.controller;

import static com.rslakra.frameworks.core.RandomUtils.nextRandomEmail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.rslakra.microservice.yatrawebapp.clients.RidesClient;
import com.rslakra.microservice.common.exception.InvalidUUIDException;
import com.rslakra.microservice.common.exception.InvalidVehicleStateException;
import com.rslakra.microservice.common.exception.NotFoundException;
import com.rslakra.microservice.yatrawebapp.util.TestHelpers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class RideControllerTest {

    @Mock
    private RidesClient mockRidesClient;

    private RideController rideController;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        rideController = new RideController(mockRidesClient);
    }

    @Test
    public void startRide_shouldCallTheRideClient()
        throws InvalidUUIDException, NotFoundException, InvalidVehicleStateException {
        Map<String, Object> expectedRequest = TestHelpers.createArbitraryMap();
        Map<String, Object> expectedResponse = TestHelpers.createArbitraryMap();

        when(mockRidesClient.startRide(expectedRequest)).thenReturn(expectedResponse);

        Map<String, Object> response = rideController.startRide(expectedRequest).getBody();

        verify(mockRidesClient).startRide(expectedRequest);

        assertEquals(expectedResponse, response);
    }

    @Test
    public void endRide_shouldCallTheRideClient() {
        Map<String, Object> expectedRequest = TestHelpers.createArbitraryMap();
        Map<String, Object> expectedResponse = TestHelpers.createArbitraryMap();

        when(mockRidesClient.endRide(expectedRequest)).thenReturn(expectedResponse);

        Map<String, Object> response = rideController.endRide(expectedRequest).getBody();

        verify(mockRidesClient).endRide(expectedRequest);

        assertEquals(expectedResponse, response);
    }

    @Test
    public void getRides_shouldCallTheRideClient() {
        String email = nextRandomEmail();
        List<Map<String, Object>>
            expectedResponse =
            IntStream.range(0, 5).mapToObj(i -> TestHelpers.createArbitraryMap()).collect(Collectors.toList());

        when(mockRidesClient.getRides(email)).thenReturn(expectedResponse);

        List<Map<String, Object>> response = rideController.getRides(email).getBody();

        verify(mockRidesClient).getRides(email);

        assertEquals(expectedResponse, response);
    }

}