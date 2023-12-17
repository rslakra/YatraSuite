package com.rslakra.microservice.yatrasuite.userservice.service;

import static com.devamatre.appsuite.core.RandomUtils.nextRandomEmail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.rslakra.microservice.yatrasuite.common.exception.NotFoundException;
import com.rslakra.microservice.yatrasuite.common.exception.UserAlreadyExistsException;
import com.rslakra.microservice.yatrasuite.userservice.persistence.entity.User;
import com.rslakra.microservice.yatrasuite.userservice.persistence.repository.PhoneRepository;
import com.rslakra.microservice.yatrasuite.userservice.persistence.repository.RoleRepository;
import com.rslakra.microservice.yatrasuite.userservice.persistence.repository.UserRepository;
import com.rslakra.microservice.yatrasuite.userservice.service.impl.UserServiceImpl;
import com.rslakra.microservice.yatrasuite.userservice.util.UserTestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Optional;

/**
 * Unit Tests for <code>UserServiceImpl</code>
 */
public class UserServiceImplTest {

    private ModelMapper modelMapper = new ModelMapper();

    @Mock
    private RoleRepository roleRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PhoneRepository phoneRepository;

    private UserService userService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        userService = new UserServiceImpl(roleRepository, userRepository, phoneRepository);
    }

    @Test
    public void addUser_shouldFail_ifTheUserExists() {
        User expected = UserTestUtils.createUser();
        when(userRepository.findByEmail(expected.getEmail())).thenReturn(Optional.of(expected));

        Exception exception = assertThrows(UserAlreadyExistsException.class, () -> {
            userService.addUser(expected.getEmail(), null, expected.getFirstName(), expected.getLastName(),
                                UserTestUtils.toPhoneNumbers(expected));
        });

        assertEquals("User email <" + expected.getEmail() + "> already exists", exception.getMessage());
        verify(userRepository, never()).save(any());
    }

    @Test
    public void addUser_shouldSaveTheUser_ifItDoesntExist() throws UserAlreadyExistsException {
        User expected = UserTestUtils.createUser();
        when(userRepository.findByEmail(expected.getEmail())).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(expected);

        userService.addUser(expected.getEmail(), null, expected.getFirstName(), expected.getLastName(),
                            UserTestUtils.toPhoneNumbers(expected));

        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userArgumentCaptor.capture());
        User user = userArgumentCaptor.getValue();
        assertEquals(expected.getEmail(), user.getEmail());
        assertEquals(expected.getFirstName(), user.getFirstName());
        assertEquals(expected.getLastName(), user.getLastName());
        assertEquals(expected.getPhones(), user.getPhones());
    }

    @Test
    public void getUser_shouldThrowAnException_ifTheUserDoesntExist() {
        String expectedEmail = nextRandomEmail();
        when(userRepository.findByEmail(expectedEmail)).thenReturn(Optional.empty());

        Exception exception = assertThrows(NotFoundException.class, () -> {
            userService.getUser(expectedEmail);
        });

        assertEquals("User email <" + expectedEmail + "> not found", exception.getMessage());
    }

    @Test
    public void getUser_shouldReturnTheUser_ifItExists() throws NotFoundException {
        User expected = UserTestUtils.createUser();

        when(userRepository.findByEmail(expected.getEmail())).thenReturn(Optional.of(expected));

        User user = userService.getUser(expected.getEmail());

        assertEquals(expected.getEmail(), user.getEmail());
        assertEquals(expected.getFirstName(), user.getFirstName());
        assertEquals(expected.getLastName(), user.getLastName());
        assertEquals(expected.getPhones(), user.getPhones());
    }

    @Test
    public void deleteUser_shouldThrowAnException_ifTheUserDoesntExist() {
        String expectedEmail = nextRandomEmail();

        when(userRepository.findByEmail(expectedEmail)).thenReturn(Optional.empty());

        Exception exception = assertThrows(NotFoundException.class, () -> {
            userService.delete(expectedEmail);
        });

        assertEquals("User email <" + expectedEmail + "> not found", exception.getMessage());
    }

    @Test
    public void deleteUser_shouldDeleteTheUser_ifItExists() throws NotFoundException {
        User expected = UserTestUtils.createUser();

        when(userRepository.findByEmail(expected.getEmail())).thenReturn(Optional.of(expected));

        userService.delete(expected.getEmail());

        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).delete(userArgumentCaptor.capture());

        User user = userArgumentCaptor.getValue();
        assertEquals(expected.getEmail(), user.getEmail());
        assertEquals(expected.getFirstName(), user.getFirstName());
        assertEquals(expected.getLastName(), user.getLastName());
        assertEquals(expected.getPhones(), user.getPhones());
    }
}
