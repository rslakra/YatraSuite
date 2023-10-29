package com.rslakra.microservice.yatrawebapp.clients;

import static com.devamatre.framework.core.RandomUtils.nextRandomEmail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.rslakra.microservice.yatrawebapp.config.RidesConfig;
import com.rslakra.microservice.yatrawebapp.util.TestHelpers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.web.client.RestTemplateBuilder;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class RidesClientTest {

    @Mock
    private RestTemplateBuilder restTemplateBuilder;
    private RidesConfig ridesConfig;
    private RidesClient ridesClient;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        ridesConfig = new RidesConfig("localhost", 1234);
        ridesClient = new RidesClient(restTemplateBuilder, ridesConfig);
    }

    @Test
    public void startRide_shouldCallTheRidesService() {
        Map<String, Object> expectedRequest = TestHelpers.createArbitraryMap();
        Map<String, Object> expectedResponse = TestHelpers.createArbitraryMap();

        when(ridesClient.getRestTemplate().postForObject(anyString(), any(), any()))
            .thenReturn(expectedResponse);

        Map<String, Object> response = ridesClient.startRide(expectedRequest);

        verify(ridesClient.getRestTemplate())
            .postForObject(ridesConfig.getServiceUrl("/api/rides/start"), expectedRequest, Map.class);

        assertEquals(expectedResponse, response);
    }

    @Test
    public void endRide_shouldCallTheRidesService() {
        Map<String, Object> expectedRequest = TestHelpers.createArbitraryMap();
        Map<String, Object> expectedResponse = TestHelpers.createArbitraryMap();

        when(ridesClient.getRestTemplate().postForObject(anyString(), any(), any()))
            .thenReturn(expectedResponse);

        Map<String, Object> response = ridesClient.endRide(expectedRequest);

        verify(ridesClient.getRestTemplate())
            .postForObject(ridesConfig.getServiceUrl("/api/rides/end"), expectedRequest, Map.class);

        assertEquals(expectedResponse, response);
    }

    @Test
    public void getRides_shouldCallTheRidesService() {
        String email = nextRandomEmail();
        List<Map<String, Object>>
            expectedResponse =
            IntStream.range(0, 5).mapToObj(i -> TestHelpers.createArbitraryMap()).collect(Collectors.toList());

        when(ridesClient.getRestTemplate().getForObject(anyString(), any()))
            .thenReturn(expectedResponse.toArray(new Map[0]));

        List<Map<String, Object>> response = ridesClient.getRides(email);

        verify(ridesClient.getRestTemplate())
            .getForObject(ridesConfig.getServiceUrl(String.format("/api/rides?email=%s", email)), Map[].class);

        for (int i = 0; i < expectedResponse.size(); i++) {
            Map<String, Object> expected = expectedResponse.get(i);
            Map<String, Object> actual = response.get(i);

            assertEquals(expected, actual);
        }
    }

}