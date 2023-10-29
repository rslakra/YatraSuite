package com.rslakra.microservice.userservice.controller;

import com.rslakra.microservice.userservice.dto.AuthRequestDTO;
import com.rslakra.microservice.userservice.dto.AuthResponseDTO;
import com.rslakra.microservice.userservice.persistence.entity.User;
import com.rslakra.microservice.common.exception.NotFoundException;
import com.rslakra.microservice.userservice.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Controller to manage login/logout activities (in an actual application, these functions would provide
 * authentication checks and other security features.)
 *
 * @author Rohtash Lakra
 * @created 2/8/23 1:57 PM
 */

@RestController
@RequestMapping("${apiPrefix}/auth")
public class AuthController {

    private static final Logger LOGGER = LogManager.getLogger(AuthController.class);

    private UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Logs in the user.
     *
     * @param authRequestDTO a POJO holding the json (email) that was passed in
     * @return json indicating if the user was authenticated or not
     * @throws NotFoundException if the passed email does not exist
     */
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO authRequestDTO)
        throws NotFoundException {
        LOGGER.info("[POST] /api/auth/login");

        User user = userService.getUser(authRequestDTO.getEmail());
        /**
         In general, perform a security check before returning a token or some other security method
         *
         */
        AuthResponseDTO authResponseDTO = new AuthResponseDTO();
        authResponseDTO.setAuthenticated(true);
        return ResponseEntity.ok(authResponseDTO);
    }
}
