package com.rslakra.microservice.yatrasuite.yatrathymeleaf.clients;

import static com.rslakra.appsuite.core.RandomUtils.nextRandomEmail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.rslakra.microservice.yatrasuite.yatrathymeleaf.config.UserServiceConfig;
import com.rslakra.microservice.yatrasuite.yatrathymeleaf.util.TestHelpers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Map;

class UserDTOClientTest {

    @Mock
    private RestTemplateBuilder restTemplateBuilder;
    private UserServiceConfig userConfig;
    private UserClient userClient;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        userConfig = new UserServiceConfig("localhost", 1234);
        userClient = new UserClient(restTemplateBuilder, userConfig);
    }

    @Test
    public void addUser_shouldPostToTheUserService() {
        Map<String, Object> expectedResponse = TestHelpers.createArbitraryMap();

        when(userClient.getRestTemplate().postForObject(anyString(), any(), any()))
            .thenReturn(expectedResponse);

        Map<String, Object> response = userClient.addUser(expectedResponse);

        verify(userClient.getRestTemplate())
            .postForObject(userConfig.getRestUrl("/api/users"), expectedResponse, Map.class);

        assertEquals(expectedResponse, response);
    }

    @Test
    public void getUser_shouldGetFromTheUserService() {
        String email = nextRandomEmail();
        Map<String, Object> expectedResponse = TestHelpers.createArbitraryMap();

        when(userClient.getRestTemplate()
                 .getForObject(userConfig.getRestUrl(String.format("/api/users/%s", email)), Map.class))
            .thenReturn(expectedResponse);

        Map<String, Object> response = userClient.getUser(email);

        verify(userClient.getRestTemplate())
            .getForObject(userConfig.getRestUrl(String.format("/api/users/%s", email)), Map.class);

        assertEquals(expectedResponse, response);
    }

    @Test
    public void deleteUser_shouldDeleteFromTheUserService() {
        String email = nextRandomEmail();
        Map<String, Object> expectedResponse = TestHelpers.createArbitraryMap();

        when(userClient.getRestTemplate()
                 .exchange(userConfig
                               .getRestUrl(String.format("/api/users/%s", email)), HttpMethod.DELETE, null,
                           Map.class))
            .thenReturn(ResponseEntity.ok(expectedResponse));

        Map<String, Object> response = userClient.deleteUser(email);

        verify(userClient.getRestTemplate())
            .exchange(userConfig
                          .getRestUrl(String.format("/api/users/%s", email)), HttpMethod.DELETE, null, Map.class);

        assertEquals(expectedResponse, response);
    }
}