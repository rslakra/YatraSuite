package com.rslakra.microservice.yatrasuite.yatrawebapp.controller;

import static com.rslakra.appsuite.core.RandomUtils.nextRandomEmail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.rslakra.microservice.yatrasuite.yatrawebapp.clients.UserClient;
import com.rslakra.microservice.yatrasuite.yatrawebapp.util.TestHelpers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Map;

class UserControllerTest {

    @Mock
    private UserClient mockUserClient;

    private UserController userController;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        userController = new UserController(mockUserClient);
    }

    @Test
    public void addUser_shouldCallTheAuthClient() {
        Map<String, Object> expectedRequest = TestHelpers.createArbitraryMap();

        when(mockUserClient.addUser(expectedRequest)).thenReturn(expectedRequest);

        Map<String, Object> response = userController.addUser(expectedRequest).getBody();

        verify(mockUserClient).addUser(expectedRequest);

        assertEquals(expectedRequest, response);
    }

    @Test
    public void getProfile_shouldCallTheAuthClient() {
        String email = nextRandomEmail();
        Map<String, Object> expectedResponse = TestHelpers.createArbitraryMap();

        when(mockUserClient.getUser(email)).thenReturn(expectedResponse);

        Map<String, Object> response = userController.getProfile(email).getBody();

        verify(mockUserClient).getUser(email);

        assertEquals(expectedResponse, response);
    }

    @Test
    public void deleteUser_shouldCallTheAuthClient() {
        String email = nextRandomEmail();
        Map<String, Object> expectedResponse = TestHelpers.createArbitraryMap();

        when(mockUserClient.deleteUser(email)).thenReturn(expectedResponse);

        Map<String, Object> response = userController.deleteUser(email).getBody();

        verify(mockUserClient).deleteUser(email);

        assertEquals(expectedResponse, response);
    }
}