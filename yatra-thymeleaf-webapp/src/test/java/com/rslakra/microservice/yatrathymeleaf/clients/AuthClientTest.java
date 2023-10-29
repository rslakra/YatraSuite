package com.rslakra.microservice.yatrathymeleaf.clients;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.rslakra.microservice.yatrathymeleaf.config.AuthServiceConfig;
import com.rslakra.microservice.yatrathymeleaf.util.TestHelpers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.web.client.RestTemplateBuilder;

import java.util.Map;

class AuthClientTest {

    @Mock
    private RestTemplateBuilder restTemplateBuilder;
    private AuthServiceConfig authConfig;
    private AuthClient authClient;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        authConfig = new AuthServiceConfig("localhost", 1234);
        authClient = new AuthClient(restTemplateBuilder, authConfig);
    }

    @Test
    public void login_shouldPostToTheAuthService() {
        Map<String, Object> expectedRequest = TestHelpers.createArbitraryMap();
        Map<String, Object> expectedResponse = TestHelpers.createArbitraryMap();

        when(authClient.getRestTemplate().postForObject(anyString(), any(), any()))
            .thenReturn(expectedResponse);

        Map<String, Object> response = authClient.login(expectedRequest);

        verify(authClient.getRestTemplate())
            .postForObject(authConfig.getRestUrl("/api/auth/login"), expectedRequest, Map.class);

        assertEquals(expectedResponse, response);
    }

}