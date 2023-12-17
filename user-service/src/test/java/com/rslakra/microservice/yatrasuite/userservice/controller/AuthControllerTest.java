package com.rslakra.microservice.yatrasuite.userservice.controller;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.rslakra.microservice.yatrasuite.common.exception.NotFoundException;
import com.rslakra.microservice.yatrasuite.userservice.dto.AuthRequestDTO;
import com.rslakra.microservice.yatrasuite.userservice.persistence.entity.User;
import com.rslakra.microservice.yatrasuite.userservice.service.UserService;
import com.rslakra.microservice.yatrasuite.userservice.util.UserTestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Tests <code>AuthController</code>
 */
class AuthControllerTest {

    @Mock
    UserService userService;
    AuthController controller;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        controller = new AuthController(userService);
    }

    @Test
    void login_shouldThrowAnException_ifTheServiceFails() throws NotFoundException {
        AuthRequestDTO dto = UserTestUtils.createCredentialsDTO();

        when(userService.getUser(dto.getEmail())).thenThrow(new NotFoundException("BOOM"));

        assertThrows(NotFoundException.class, () -> {
            controller.login(dto);
        });
    }

    @Test
    void login_shouldAuthenticateTheUser_ifItExists() throws NotFoundException {
        User user = UserTestUtils.createUser();
        AuthRequestDTO dto = new AuthRequestDTO();
        dto.setEmail(user.getEmail());

        when(userService.getUser(dto.getEmail())).thenReturn(user);

        Boolean authenticated = controller.login(dto).getBody().isAuthenticated();

        verify(userService).getUser(dto.getEmail());
        assertTrue(authenticated);
    }
}