package com.rslakra.microservice.userservice.controller;

import static com.rslakra.frameworks.core.RandomUtils.nextRandomEmail;
import static com.rslakra.microservice.userservice.util.UserTestUtils.createUser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.rslakra.microservice.common.exception.NotFoundException;
import com.rslakra.microservice.common.exception.UserAlreadyExistsException;
import com.rslakra.microservice.userservice.dto.UserDTO;
import com.rslakra.microservice.userservice.persistence.entity.User;
import com.rslakra.microservice.userservice.service.UserService;
import com.rslakra.microservice.userservice.util.UserTestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Tests <code>UserApiController</code>
 */
class UserApiControllerTest {

    @Mock
    UserService userService;
    UserApiController controller;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        controller = new UserApiController(userService);
    }

    @Test
    void getUser_shouldThrowAnException_ifTheServiceFails() throws NotFoundException {
        String email = nextRandomEmail();

        when(userService.getUser(email)).thenThrow(new NotFoundException("BOOM"));

        assertThrows(NotFoundException.class, () -> {
            controller.getUser(email);
        });
    }

    @Test
    void getUser_shouldReturnTheUser_ifItExists() throws NotFoundException {
        User expected = createUser();
        when(userService.getUser(expected.getEmail())).thenReturn(expected);

        UserDTO user = controller.getUser(expected.getEmail()).getBody().getUserDTO();

        verify(userService).getUser(expected.getEmail());
        assertEquals(expected.getEmail(), user.getEmail());
        assertEquals(expected.getFirstName(), user.getFirstName());
        assertEquals(expected.getLastName(), user.getLastName());
//        assertEquals(expected.getPhones(), user.getPhoneNumbers());
    }

    @Test
    void addUser_shouldThrowAnException_ifTheServiceFails() throws UserAlreadyExistsException {
        UserDTO dto = UserTestUtils.createUserDTO();

        when(userService.addUser(dto.getEmail(), dto.getEmail(), dto.getFirstName(), dto.getLastName(),
                                 dto.getPhoneNumbers())).thenThrow(new UserAlreadyExistsException("BOOM"));

        assertThrows(UserAlreadyExistsException.class, () -> {
            controller.addUser(dto);
        });
    }

    @Test
    void addUser_shouldCallAddUserOnTheService() throws UserAlreadyExistsException {
        User user = UserTestUtils.createUser();
        UserDTO dto = UserTestUtils.toUserDTO(user);

        when(userService.addUser(dto.getEmail(), dto.getEmail(), dto.getFirstName(), dto.getLastName(),
                                 dto.getPhoneNumbers())).thenReturn(user);

        UserDTO result = controller.addUser(dto).getBody();

        verify(userService).addUser(dto.getEmail(), dto.getEmail(), dto.getFirstName(), dto.getLastName(),
                                    dto.getPhoneNumbers());
        assertEquals(dto.getEmail(), result.getEmail());
        assertEquals(dto.getFirstName(), result.getFirstName());
        assertEquals(dto.getLastName(), result.getLastName());
//        assertEquals(dto.getPhoneNumbers(), result.getPhoneNumbers());
    }

    @Test
    void deleteUser_shouldThrowAnException_ifTheServiceFails() throws NotFoundException {
        String email = nextRandomEmail();

        doThrow(new NotFoundException("BOOM")).when(userService).delete(email);

        assertThrows(NotFoundException.class, () -> {
            controller.deleteUser(email);
        });
    }

    @Test
    void deleteUser_shouldCallDeleteUserOnTheService() throws NotFoundException {
        String email = nextRandomEmail();

        controller.deleteUser(email);

        verify(userService).delete(email);
    }
}