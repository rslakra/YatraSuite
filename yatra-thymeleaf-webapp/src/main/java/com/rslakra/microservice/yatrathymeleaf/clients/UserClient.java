package com.rslakra.microservice.yatrathymeleaf.clients;

import com.rslakra.microservice.yatrathymeleaf.framework.ui.config.AbstractServiceConfig;
import com.rslakra.microservice.yatrathymeleaf.config.UserServiceConfig;
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

    private final UserServiceConfig serviceConfig;

    /**
     * @param restTemplateBuilder
     * @param serviceConfig
     */
    @Autowired
    public UserClient(RestTemplateBuilder restTemplateBuilder, UserServiceConfig serviceConfig) {
        super(restTemplateBuilder);
        this.serviceConfig = serviceConfig;
    }

    /**
     * @return
     */
    @Override
    public AbstractServiceConfig getServiceConfig() {
        return serviceConfig;
    }

    /**
     * @param userDTO
     * @return
     */
    public Map<String, Object> addUser(Map<String, Object> userDTO) {
        LOGGER.debug("addUser({})", userDTO);
        String uri = getServiceConfig().getRestUrl("/users");
        Map<String, Object> response = getRestTemplate().postForObject(uri, userDTO, Map.class);
        return response;
    }

    /**
     * @param email
     * @return
     */
    public Map<String, Object> getUser(String email) {
        LOGGER.debug("getUser({})", email);
        String uri = getServiceConfig().getRestUrl(String.format("/users/%s", email));
        Map<String, Object> response = getRestTemplate().getForObject(uri, Map.class);
        return response;
    }

    /**
     * @param email
     * @return
     */
    public Map<String, Object> deleteUser(String email) {
        LOGGER.debug("deleteUser({})", email);
        String uri = getServiceConfig().getRestUrl(String.format("/users/%s", email));
        // Using an exchange instead of a delete because it allows me to return a body. Delete just returns void.
        Map<String, Object> response = getRestTemplate().exchange(uri, HttpMethod.DELETE, null, Map.class).getBody();
        return response;
    }
}
