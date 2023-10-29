package com.rslakra.microservice.userservice.controller;

import com.rslakra.frameworks.core.BeanUtils;
import com.rslakra.microservice.userservice.Constants;
import com.rslakra.microservice.userservice.dto.UserDTO;
import com.rslakra.microservice.userservice.dto.UserResponseDTO;
import com.rslakra.microservice.userservice.persistence.entity.User;
import com.rslakra.microservice.common.dto.MessagesDTO;
import com.rslakra.microservice.common.exception.NotFoundException;
import com.rslakra.microservice.common.exception.UserAlreadyExistsException;
import com.rslakra.microservice.userservice.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

/**
 * Manages the user activities.
 *
 * @author Rohtash Lakra
 * @created 7/20/23 10:11 PM
 */
@RestController
@RequestMapping("${apiPrefix}/users")
public class UserApiController {

    private static final Logger LOGGER = LogManager.getLogger(UserApiController.class);
    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    private UserService userService;

    @Autowired
    public UserApiController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Registers a new user (add a User entity).
     *
     * @param userDTO a POJO holding the json that was passed in containing the user information
     * @return the email of the added user
     * @throws UserAlreadyExistsException if the email already exists
     */
    @PostMapping
    public ResponseEntity<UserDTO> addUser(@RequestBody @Validated UserDTO userDTO) throws UserAlreadyExistsException {
        LOGGER.info("[POST] /api/users");

        User
            user =
            userService.addUser(userDTO.getEmail(), userDTO.getEmail(), userDTO.getFirstName(), userDTO.getLastName(),
                                userDTO.getPhoneNumbers());

        return ResponseEntity.ok(toUserDto(user));
    }

    /**
     * Gets a user.
     *
     * @param email the email of the user to retrieve
     * @return Json with the details about the user
     * @throws NotFoundException if the email does not exist
     */
    @GetMapping("/{email}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable String email) throws NotFoundException {
        LOGGER.info("[GET] /api/users/" + email);
        User user = userService.getUser(email);
        return ResponseEntity.ok(toDto(user));
    }

    /**
     * Deletes a user.
     *
     * @param email the email of the user to delete
     * @return a message indicated the user was deleted
     * @throws NotFoundException if the email does not exist
     */
    @DeleteMapping("/{email}")
    public ResponseEntity<MessagesDTO> deleteUser(@PathVariable String email) throws NotFoundException {
        LOGGER.info("[DELETE] /api/users/" + email);
        userService.delete(email);
        MessagesDTO messagesDTO = new MessagesDTO();
        messagesDTO.addMessage(Constants.MSG_DELETED_EMAIL);
        return ResponseEntity.ok(messagesDTO);
    }

    /**
     * @param user
     * @return
     */
    private UserDTO toUserDto(User user) {
        UserDTO userDTO = MODEL_MAPPER.map(user, UserDTO.class);
        /* copy phone numbers, if available. */
        if (BeanUtils.isNotEmpty(user.getPhones())) {
            userDTO.setPhoneNumbers(
                user.getPhones().stream().map(phone -> phone.getNumber()).collect(Collectors.toList()));
        }

        return userDTO;
    }

    /**
     * Converts the User entity object to a Data Transfer Object.
     *
     * @param user the User entity object
     * @return UserDTO
     */
    private UserResponseDTO toDto(User user) {
        UserDTO userDTO = toUserDto(user);
        return new UserResponseDTO(userDTO, null);
    }
}

