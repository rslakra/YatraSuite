package com.rslakra.microservice.yatrawebapp.controller;

import com.rslakra.microservice.yatrawebapp.clients.UserClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

import java.util.Map;

/**
 * REST Controller to manage user activities
 */

@RestController
@RequestMapping("${apiPrefix}/users")
public class UserController {

    private UserClient userClient;
    private final Logger LOGGER = LogManager.getLogger(this.getClass());

    @Autowired
    public UserController(UserClient userClient) {
        this.userClient = userClient;
    }

    /**
     * Registers a new user (add a User entity).
     *
     * @param userDTO a POJO holding the json that was passed in containing the user information
     * @return the email of the added user
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> addUser(@RequestBody @Validated Map<String, Object> userDTO) {
        LOGGER.info("[POST] /ui/users");
        return ResponseEntity.ok(userClient.addUser(userDTO));
    }

    /**
     * Gets a user.
     *
     * @param email the email of the user to retrieve
     * @return Json with the details about the user
     * @throws NotFoundException if the email does not exist
     */
    @GetMapping("/{email}")
    public ResponseEntity<Map<String, Object>> getProfile(@PathVariable String email) {
        LOGGER.info("[GET] /ui/users/{email}");
        return ResponseEntity.ok(userClient.getUser(email));
    }

    /**
     * Deletes a user.
     *
     * @param email the email of the user to delete
     * @return a message indicated the user was deleted
     */
    @DeleteMapping("/{email}")
    public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable String email) {
        LOGGER.info("[DELETE] /ui/users/{email}");
        return ResponseEntity.ok(userClient.deleteUser(email));
    }
}

