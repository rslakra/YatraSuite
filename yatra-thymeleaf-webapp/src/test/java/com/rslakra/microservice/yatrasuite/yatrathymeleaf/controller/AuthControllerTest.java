package com.rslakra.microservice.yatrasuite.yatrathymeleaf.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.rslakra.microservice.yatrasuite.yatrathymeleaf.clients.AuthClient;
import com.rslakra.microservice.yatrasuite.yatrathymeleaf.util.TestHelpers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Map;

class AuthControllerTest {

    @Mock
    private AuthClient mockAuthClient;

    private AuthController authController;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        authController = new AuthController(mockAuthClient);
    }

    @Test
    public void login_shouldCallTheAuthClient() {
        Map<String, Object> expectedRequest = TestHelpers.createArbitraryMap();
        Map<String, Object> expectedResponse = TestHelpers.createArbitraryMap();

        when(mockAuthClient.login(expectedRequest)).thenReturn(expectedResponse);

        Map<String, Object> response = authController.login(expectedRequest).getBody();

        verify(mockAuthClient).login(expectedRequest);
        assertEquals(expectedResponse, response);
    }
}