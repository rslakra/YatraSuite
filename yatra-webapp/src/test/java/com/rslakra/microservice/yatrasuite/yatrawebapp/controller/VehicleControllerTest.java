package com.rslakra.microservice.yatrasuite.yatrawebapp.controller;

import static com.devamatre.appsuite.core.RandomUtils.nextRandomUuid;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.rslakra.microservice.yatrasuite.common.exception.InvalidUUIDException;
import com.rslakra.microservice.yatrasuite.yatrawebapp.clients.VehicleClient;
import com.rslakra.microservice.yatrasuite.yatrawebapp.util.TestHelpers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class VehicleControllerTest {

    @Mock
    private VehicleClient mockVehicleClient;

    private VehicleController vehicleController;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        vehicleController = new VehicleController(mockVehicleClient);
    }

    @Test
    void addVehicle_shouldCallTheVehicleClient() {
        Map<String, Object> expectedRequest = TestHelpers.createArbitraryMap();
        Map<String, Object> expectedResponse = TestHelpers.createArbitraryMap();

        when(mockVehicleClient.addVehicle(expectedRequest)).thenReturn(expectedResponse);

        Map<String, Object> response = vehicleController.addVehicle(expectedRequest).getBody();

        verify(mockVehicleClient).addVehicle(expectedRequest);

        assertEquals(expectedResponse, response);
    }

    @Test
    void removeVehicle_shouldThrowAnException_ifTheIdIsInvalid() {
        assertThrows(InvalidUUIDException.class, () -> vehicleController.removeVehicle("INVALID"));

        verify(mockVehicleClient, never()).removeVehicle(any());
    }

    @Test
    void removeVehicle_shouldCallTheVehicleClient() throws InvalidUUIDException {
        UUID vehicleId = nextRandomUuid();
        Map<String, Object> expectedResponse = TestHelpers.createArbitraryMap();

        when(mockVehicleClient.removeVehicle(vehicleId)).thenReturn(expectedResponse);

        Map<String, Object> response = vehicleController.removeVehicle(vehicleId.toString()).getBody();

        verify(mockVehicleClient).removeVehicle(vehicleId);

        assertEquals(expectedResponse, response);
    }

    @Test
    public void getVehiclesWithLocation_shouldCallTheVehicleClient() {
        int maxVehicles = 5;
        List<Map<String, Object>>
            expectedResponse =
            IntStream.range(0, maxVehicles).mapToObj(i -> TestHelpers.createArbitraryMap())
                .collect(Collectors.toList());

        when(mockVehicleClient.getVehiclesWithLocation(maxVehicles)).thenReturn(expectedResponse);

        List<Map<String, Object>> response = vehicleController.getVehiclesWithLocation(maxVehicles).getBody();

        verify(mockVehicleClient).getVehiclesWithLocation(maxVehicles);

        for (int i = 0; i < maxVehicles; i++) {
            assertEquals(expectedResponse.get(i), response.get(i));
        }
    }

    @Test
    public void getVehicleWithLocation_shouldThrowAnExceptionIfTheVehicleIdIsInvalid() throws InvalidUUIDException {
        assertThrows(InvalidUUIDException.class, () -> {
            vehicleController.getVehicleWithLocation("Invalid").getBody();
        });

        verify(mockVehicleClient, never()).getVehicleWithLocation(any());
    }

    @Test
    public void getVehicleWithLocation_shouldGetFromTheVehicleClient() throws InvalidUUIDException {
        UUID vehicleId = nextRandomUuid();
        Map<String, Object> expectedResponse = TestHelpers.createArbitraryMap();

        when(mockVehicleClient.getVehicleWithLocation(vehicleId)).thenReturn(expectedResponse);

        Map<String, Object> response = vehicleController.getVehicleWithLocation(vehicleId.toString()).getBody();

        verify(mockVehicleClient).getVehicleWithLocation(vehicleId);

        assertEquals(expectedResponse, response);
    }

    @Test
    public void getVehicleWithHistory_shouldThrowAnExceptionIfTheVehicleIdIsInvalid() throws InvalidUUIDException {
        assertThrows(InvalidUUIDException.class, () -> {
            vehicleController.getVehicleWithHistory("Invalid").getBody();
        });

        verify(mockVehicleClient, never()).getVehicleWithHistory(any());
    }

    @Test
    public void getVehicleWithHistory_shouldGetFromTheVehicleClient() throws InvalidUUIDException {
        UUID vehicleId = nextRandomUuid();
        Map<String, Object> expectedResponse = TestHelpers.createArbitraryMap();

        when(mockVehicleClient.getVehicleWithHistory(vehicleId)).thenReturn(expectedResponse);

        Map<String, Object> response = vehicleController.getVehicleWithHistory(vehicleId.toString()).getBody();

        verify(mockVehicleClient).getVehicleWithHistory(vehicleId);

        assertEquals(expectedResponse, response);
    }
}