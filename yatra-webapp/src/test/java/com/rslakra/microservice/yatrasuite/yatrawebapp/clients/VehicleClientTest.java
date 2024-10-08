package com.rslakra.microservice.yatrasuite.yatrawebapp.clients;

import static com.rslakra.appsuite.core.RandomUtils.nextRandomUuid;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.rslakra.microservice.yatrasuite.yatrawebapp.config.VehicleConfig;
import com.rslakra.microservice.yatrasuite.yatrawebapp.util.TestHelpers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class VehicleClientTest {

    @Mock
    private RestTemplateBuilder restTemplateBuilder;
    private VehicleConfig vehicleConfig;
    private VehicleClient vehicleClient;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        vehicleConfig = new VehicleConfig("localhost", 1234);
        vehicleClient = new VehicleClient(restTemplateBuilder, vehicleConfig);
    }

    @Test
    public void addVehicle_shouldPostToTheVehicleService() {
        Map<String, Object> expectedRequest = TestHelpers.createArbitraryMap();
        Map<String, Object> expectedResponse = TestHelpers.createArbitraryMap();

        when(vehicleClient.getRestTemplate().postForObject(anyString(), any(), any()))
            .thenReturn(expectedResponse);

        Map<String, Object> response = vehicleClient.addVehicle(expectedRequest);

        verify(vehicleClient.getRestTemplate())
            .postForObject(vehicleConfig.getServiceUrl("/api/vehicles"), expectedRequest, Map.class);

        assertEquals(expectedResponse, response);
    }

    @Test
    public void removeVehicle_shouldPostToTheVehicleService() {
        UUID vehicleId = nextRandomUuid();
        Map<String, Object> expectedResponse = TestHelpers.createArbitraryMap();

        when(vehicleClient.getRestTemplate()
                 .exchange(vehicleConfig
                               .getServiceUrl(String.format("%s/%s", "/api/vehicles", vehicleId)), HttpMethod.DELETE,
                           null, Map.class))
            .thenReturn(ResponseEntity.ok(expectedResponse));

        Map<String, Object> response = vehicleClient.removeVehicle(vehicleId);

        verify(vehicleClient.getRestTemplate())
            .exchange(vehicleConfig
                          .getServiceUrl(String.format("%s/%s", "/api/vehicles", vehicleId)), HttpMethod.DELETE, null,
                      Map.class);

        assertEquals(expectedResponse, response);
    }

    @Test
    public void getVehiclesWithLocation_shouldGetFromTheVehicleService() {
        int maxVehicles = 5;
        List<Map<String, Object>>
            expectedResponse =
            IntStream.range(0, maxVehicles).mapToObj(i -> TestHelpers.createArbitraryMap())
                .collect(Collectors.toList());

        when(vehicleClient.getRestTemplate().getForObject(anyString(), any()))
            .thenReturn(expectedResponse.toArray(new Map[0]));

        List<Map<String, Object>> response = vehicleClient.getVehiclesWithLocation(maxVehicles);

        verify(vehicleClient.getRestTemplate())
            .getForObject(vehicleConfig
                              .getServiceUrl(String.format("%s?max_vehicles=%s", "/api/vehicles", maxVehicles)),
                          Map[].class);

        for (int i = 0; i < maxVehicles; i++) {
            assertEquals(expectedResponse.get(i), response.get(i));
        }
    }

    @Test
    public void getVehicleWithLocation_shouldGetFromTheVehicleService() {
        UUID vehicleId = nextRandomUuid();
        Map<String, Object> expectedResponse = TestHelpers.createArbitraryMap();

        when(vehicleClient.getRestTemplate().getForObject(anyString(), any()))
            .thenReturn(expectedResponse);

        Map<String, Object> response = vehicleClient.getVehicleWithLocation(vehicleId);

        verify(vehicleClient.getRestTemplate())
            .getForObject(vehicleConfig
                              .getServiceUrl(String.format("%s/%s", "/api/vehicles/location", vehicleId.toString())),
                          Map.class);

        assertEquals(expectedResponse, response);
    }

    @Test
    public void getVehicleWithHistory_shouldGetFromTheVehicleService() {
        UUID vehicleId = nextRandomUuid();
        Map<String, Object> expectedResponse = TestHelpers.createArbitraryMap();

        when(vehicleClient.getRestTemplate().getForObject(anyString(), any()))
            .thenReturn(expectedResponse);

        Map<String, Object> response = vehicleClient.getVehicleWithHistory(vehicleId);

        verify(vehicleClient.getRestTemplate())
            .getForObject(vehicleConfig
                              .getServiceUrl(String.format("%s/%s", "/api/vehicles", vehicleId.toString())), Map.class);

        assertEquals(expectedResponse, response);
    }
}