package com.rslakra.microservice.yatrasuite.yatrathymeleaf.clients;

import static com.rslakra.appsuite.core.RandomUtils.nextRandomEmail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.rslakra.microservice.yatrasuite.yatrathymeleaf.config.RideServiceConfig;
import com.rslakra.microservice.yatrasuite.yatrathymeleaf.util.TestHelpers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.web.client.RestTemplateBuilder;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class RideClientTest {

    @Mock
    private RestTemplateBuilder restTemplateBuilder;
    private RideServiceConfig ridesConfig;
    private RideClient rideClient;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        ridesConfig = new RideServiceConfig("localhost", 1234);
        rideClient = new RideClient(restTemplateBuilder, ridesConfig);
    }

    @Test
    public void startRide_shouldCallTheRidesService() {
        Map<String, Object> expectedRequest = TestHelpers.createArbitraryMap();
        Map<String, Object> expectedResponse = TestHelpers.createArbitraryMap();

        when(rideClient.getRestTemplate().postForObject(anyString(), any(), any()))
            .thenReturn(expectedResponse);

        Map<String, Object> response = rideClient.startRide(expectedRequest);

        verify(rideClient.getRestTemplate())
            .postForObject(ridesConfig.getRestUrl("/api/rides/start"), expectedRequest, Map.class);

        assertEquals(expectedResponse, response);
    }

    @Test
    public void endRide_shouldCallTheRidesService() {
        Map<String, Object> expectedRequest = TestHelpers.createArbitraryMap();
        Map<String, Object> expectedResponse = TestHelpers.createArbitraryMap();

        when(rideClient.getRestTemplate().postForObject(anyString(), any(), any()))
            .thenReturn(expectedResponse);

        Map<String, Object> response = rideClient.endRide(expectedRequest);

        verify(rideClient.getRestTemplate())
            .postForObject(ridesConfig.getRestUrl("/api/rides/end"), expectedRequest, Map.class);

        assertEquals(expectedResponse, response);
    }

    @Test
    public void getRides_shouldCallTheRidesService() {
        String email = nextRandomEmail();
        List<Map<String, Object>>
            expectedResponse =
            IntStream.range(0, 5).mapToObj(i -> TestHelpers.createArbitraryMap()).collect(Collectors.toList());

        when(rideClient.getRestTemplate().getForObject(anyString(), any()))
            .thenReturn(expectedResponse.toArray(new Map[0]));

        List<Map<String, Object>> response = rideClient.getRides(email);

        verify(rideClient.getRestTemplate())
            .getForObject(ridesConfig.getRestUrl(String.format("/api/rides?email=%s", email)), Map[].class);

        for (int i = 0; i < expectedResponse.size(); i++) {
            Map<String, Object> expected = expectedResponse.get(i);
            Map<String, Object> actual = response.get(i);

            assertEquals(expected, actual);
        }
    }

}