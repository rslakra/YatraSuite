package com.rslakra.microservice.yatrathymeleaf.clients;

import com.rslakra.microservice.yatrathymeleaf.framework.ui.config.AbstractServiceConfig;
import com.rslakra.microservice.yatrathymeleaf.config.AuthServiceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AuthClient extends AbstractRestClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthClient.class);

    private final AuthServiceConfig serviceConfig;

    /**
     * @param restTemplateBuilder
     * @param authConfig
     */
    @Autowired
    public AuthClient(RestTemplateBuilder restTemplateBuilder, AuthServiceConfig authConfig) {
        super(restTemplateBuilder);
        this.serviceConfig = authConfig;
    }

    /**
     * @return
     */
    @Override
    public AbstractServiceConfig getServiceConfig() {
        return serviceConfig;
    }

    /**
     * @param authRequestMap
     * @return
     */
    public Map<String, Object> login(Map<String, Object> authRequestMap) {
        LOGGER.debug("login({})", authRequestMap);
        String uri = getServiceConfig().getRestUrl("/auth/login");
        Map<String, Object> response = getRestTemplate().postForObject(uri, authRequestMap, Map.class);
        return response;
    }
}
