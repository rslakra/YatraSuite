package com.rslakra.microservice.yatrasuite.yatrawebapp.clients;

import com.rslakra.microservice.yatrasuite.yatrawebapp.config.AuthConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AuthClient extends AbstractRestClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthClient.class);

    private AuthConfig config;

    /**
     * @param restTemplateBuilder
     * @param authConfig
     */
    @Autowired
    public AuthClient(RestTemplateBuilder restTemplateBuilder, AuthConfig authConfig) {
        super(restTemplateBuilder);
        this.config = authConfig;
    }

    /**
     * @param authRequestMap
     * @return
     */
    public Map<String, Object> login(Map<String, Object> authRequestMap) {
        LOGGER.debug("login({})", authRequestMap);
        String uri = config.getServiceUrl("/auth/login");
        Map<String, Object> response = getRestTemplate().postForObject(uri, authRequestMap, Map.class);
        return response;
    }
}
