package com.rslakra.microservice.yatrasuite.yatrawebapp.controller;

import static com.rslakra.appsuite.core.RandomUtils.nextRandomEmail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.rslakra.microservice.yatrasuite.common.exception.InvalidUUIDException;
import com.rslakra.microservice.yatrasuite.common.exception.InvalidVehicleStateException;
import com.rslakra.microservice.yatrasuite.common.exception.NotFoundException;
import com.rslakra.microservice.yatrasuite.yatrawebapp.clients.RideClient;
import com.rslakra.microservice.yatrasuite.yatrawebapp.util.TestHelpers;
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
    private RideClient mockRideClient;

    private RideController rideController;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        rideController = new RideController(mockRideClient);
    }

    @Test
    public void startRide_shouldCallTheRideClient()
        throws InvalidUUIDException, NotFoundException, InvalidVehicleStateException {
        Map<String, Object> expectedRequest = TestHelpers.createArbitraryMap();
        Map<String, Object> expectedResponse = TestHelpers.createArbitraryMap();

        when(mockRideClient.startRide(expectedRequest)).thenReturn(expectedResponse);

        Map<String, Object> response = rideController.startRide(expectedRequest).getBody();

        verify(mockRideClient).startRide(expectedRequest);

        assertEquals(expectedResponse, response);
    }

    @Test
    public void endRide_shouldCallTheRideClient() {
        Map<String, Object> expectedRequest = TestHelpers.createArbitraryMap();
        Map<String, Object> expectedResponse = TestHelpers.createArbitraryMap();

        when(mockRideClient.endRide(expectedRequest)).thenReturn(expectedResponse);

        Map<String, Object> response = rideController.endRide(expectedRequest).getBody();

        verify(mockRideClient).endRide(expectedRequest);

        assertEquals(expectedResponse, response);
    }

    @Test
    public void getRides_shouldCallTheRideClient() {
        String email = nextRandomEmail();
        List<Map<String, Object>>
            expectedResponse =
            IntStream.range(0, 5).mapToObj(i -> TestHelpers.createArbitraryMap()).collect(Collectors.toList());

        when(mockRideClient.getRides(email)).thenReturn(expectedResponse);

        List<Map<String, Object>> response = rideController.getRides(email).getBody();

        verify(mockRideClient).getRides(email);

        assertEquals(expectedResponse, response);
    }

}