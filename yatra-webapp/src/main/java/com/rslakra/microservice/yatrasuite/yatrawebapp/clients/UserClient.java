package com.rslakra.microservice.yatrasuite.yatrawebapp.clients;

import com.rslakra.microservice.yatrasuite.yatrawebapp.config.UserConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserClient extends AbstractRestClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserClient.class);

    private UserConfig config;

    @Autowired
    public UserClient(RestTemplateBuilder restTemplateBuilder, UserConfig config) {
        super(restTemplateBuilder);
        this.config = config;
    }

    /**
     * @param userDTO
     * @return
     */
    public Map<String, Object> addUser(Map<String, Object> userDTO) {
        LOGGER.debug("addUser({})", userDTO);
        String uri = config.getServiceUrl("/users");
        Map<String, Object> response = getRestTemplate().postForObject(uri, userDTO, Map.class);
        return response;
    }

    /**
     * @param email
     * @return
     */
    public Map<String, Object> getUser(String email) {
        LOGGER.debug("getUser({})", email);
        String uri = config.getServiceUrl(String.format("/users/%s", email));
        Map<String, Object> response = getRestTemplate().getForObject(uri, Map.class);
        return response;
    }

    /**
     * @param email
     * @return
     */
    public Map<String, Object> deleteUser(String email) {
        LOGGER.debug("deleteUser({})", email);
        String uri = config.getServiceUrl(String.format("/users/%s", email));
        // Using an exchange instead of a delete because it allows me to return a body. Delete just returns void.
        Map<String, Object> response = getRestTemplate().exchange(uri, HttpMethod.DELETE, null, Map.class).getBody();
        return response;
    }
}
