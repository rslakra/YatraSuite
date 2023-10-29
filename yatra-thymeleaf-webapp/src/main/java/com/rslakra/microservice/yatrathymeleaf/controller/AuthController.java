package com.rslakra.microservice.yatrathymeleaf.controller;

import com.rslakra.microservice.yatrathymeleaf.clients.AuthClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * REST Controller to manage login/logout activities (in an actual application, these functions would provide
 * authentication checks and other security features.)
 */

@RestController
@RequestMapping("${uiPrefix}/auth")
public class AuthController {

    private AuthClient authClient;
    private final Logger LOGGER = LogManager.getLogger(this.getClass());

    @Autowired
    public AuthController(AuthClient authClient) {
        this.authClient = authClient;
    }

    /**
     * Logs in the user.
     *
     * @param authRequest a POJO holding the json (email) that was passed in
     * @return json indicating if the user was authenticated or not
     */
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, Object> authRequest) {
        LOGGER.info("[POST] /ui/auth/login");
        return ResponseEntity.ok(authClient.login(authRequest));
    }
}
